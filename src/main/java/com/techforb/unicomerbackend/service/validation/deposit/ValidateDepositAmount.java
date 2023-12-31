package com.techforb.unicomerbackend.service.validation.deposit;

import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;

@Service
public class ValidateDepositAmount implements DepositValidation {

	@Override
	public void validate(UserTransactionRequestDTO dto) {
		if (dto.getTransactionAmount().doubleValue() <= 0.0) {
			throw new TransferException("invalid transfer amount");
		}
	}

}
