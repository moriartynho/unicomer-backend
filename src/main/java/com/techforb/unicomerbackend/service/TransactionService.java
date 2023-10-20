package com.techforb.unicomerbackend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.DepositRequestDTO;
import com.techforb.unicomerbackend.dto.TransferRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.model.UserTransfer;
import com.techforb.unicomerbackend.model.enums.TransactionType;
import com.techforb.unicomerbackend.repository.UserRepository;
import com.techforb.unicomerbackend.repository.UserTransnferRepository;
import com.techforb.unicomerbackend.service.validation.deposit.DepositValidation;
import com.techforb.unicomerbackend.service.validation.transfer.TransferValidation;

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

	public void makeDeposit(DepositRequestDTO depositDTO) {

		try {
			User user = validateDeposit(depositDTO);
			UserTransfer deposit = new UserTransfer(null, LocalDateTime.now(), depositDTO.getDepositAmount(),
					TransactionType.DEPOSIT, user);
			user.setBalance(
					new BigDecimal(user.getBalance().doubleValue() + depositDTO.getDepositAmount().doubleValue()));
			transnferRepository.save(deposit);
			userRepository.save(user);
		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}
	}

	private User validateDeposit(DepositRequestDTO depositDTO) {
		try {
			Optional<User> user = userRepository.findById(depositDTO.getAccountId());
			this.depositValidatons.forEach(v -> v.validate(depositDTO, user));
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
