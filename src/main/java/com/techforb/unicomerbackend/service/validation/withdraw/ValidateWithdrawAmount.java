package com.techforb.unicomerbackend.service.validation.withdraw;

import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;

@Service
public class ValidateWithdrawAmount implements WithdrawValidation {

	public void validate(UserTransactionRequestDTO dto) {
		if (dto.getTransactionAmount().doubleValue() <= 0.0) {
			throw new TransferException("invalid withdraw amount");
		}
	}

}
