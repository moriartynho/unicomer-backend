package com.techforb.unicomerbackend.service.validation.withdraw;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.repository.UserRepository;

@Service
public class ValidateUserBalance implements WithdrawValidation {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void validate(UserTransactionRequestDTO dto) {
		if(userRepository.findById(dto.getAccountId()).get().getBalance().subtract(dto.getTransactionAmount()).compareTo(BigDecimal.ZERO) < 0) {
			throw new TransferException("insuficient funds");
		}
		
	}

}
