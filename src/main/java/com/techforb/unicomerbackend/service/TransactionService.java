package com.techforb.unicomerbackend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;
import com.techforb.unicomerbackend.dto.TransferRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.model.UserTransfer;
import com.techforb.unicomerbackend.model.enums.TransactionType;
import com.techforb.unicomerbackend.repository.UserRepository;
import com.techforb.unicomerbackend.repository.UserTransnferRepository;
import com.techforb.unicomerbackend.service.validation.deposit.DepositValidation;
import com.techforb.unicomerbackend.service.validation.transfer.TransferValidation;
import com.techforb.unicomerbackend.service.validation.withdraw.WithdrawValidation;

@Service
public class TransactionService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTransnferRepository transnferRepository;

	@Autowired
	private List<TransferValidation> transferValidations;

	@Autowired
	private List<DepositValidation> depositValidatons;
	
	@Autowired
	private List<WithdrawValidation> withdrawValidations;

	public void makeTransfer(TransferRequestDTO transferDTO) {
		try {
			User[] users = validateTransfer(transferDTO);

			UserTransfer userTransfer = new UserTransfer(null, LocalDateTime.now(), transferDTO.getTransferAmount(),
					TransactionType.TRANSFER, users[1]);

			users[0].setBalance(new BigDecimal(
					users[0].getBalance().doubleValue() - transferDTO.getTransferAmount().doubleValue()));

			users[1].setBalance(new BigDecimal(
					users[1].getBalance().doubleValue() + transferDTO.getTransferAmount().doubleValue()));

			transnferRepository.save(userTransfer);
			Arrays.asList(users[0], users[1]).forEach(u -> userRepository.save(u));
			;

		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}
	}

	public void makeDeposit(UserTransactionRequestDTO depositDTO) {

		try {
			User user = validateUserTransaction(depositDTO, TransactionType.DEPOSIT);
			UserTransfer deposit = new UserTransfer(null, LocalDateTime.now(), depositDTO.getTransactionAmount(),
					TransactionType.DEPOSIT, user);
			user.setBalance(
					new BigDecimal(user.getBalance().doubleValue() + depositDTO.getTransactionAmount().doubleValue()));
			transnferRepository.save(deposit);
			userRepository.save(user);
		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}
	}

	public void makeWithdraw(UserTransactionRequestDTO depositDTO) {
		try {
			User user = validateUserTransaction(depositDTO, TransactionType.WITHDRAW);
			UserTransfer withdraw = new UserTransfer(null, LocalDateTime.now(), depositDTO.getTransactionAmount(),
					TransactionType.WITHDRAW, user);
			user.setBalance(
					new BigDecimal(user.getBalance().doubleValue() - depositDTO.getTransactionAmount().doubleValue()));
			transnferRepository.save(withdraw);
			userRepository.save(user);
		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}

	}

	private User validateUserTransaction(UserTransactionRequestDTO depositDTO, TransactionType transactionType) {
		try {
			
			Optional<User> user = userRepository.findById(depositDTO.getAccountId());
			
			if (transactionType.equals(TransactionType.DEPOSIT)) {
				this.depositValidatons.forEach(v -> v.validate(depositDTO));
				return user.get();
			} else
				this.withdrawValidations.forEach(v -> v.validate(depositDTO));
				return user.get();
		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}
	}

	private User[] validateTransfer(TransferRequestDTO transferDTO) {
		try {

			Optional<User> sourceAccount = userRepository.findById(transferDTO.getSourceAccountId());
			Optional<User> targetAccount = userRepository.findById(transferDTO.getTargetAccountId());

			this.transferValidations
					.forEach(v -> v.validate(sourceAccount, targetAccount, transferDTO.getTransferAmount()));

			User[] users = { sourceAccount.get(), targetAccount.get() };
			return users;
		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}

	}

}
