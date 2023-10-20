package com.techforb.unicomerbackend.service.validation.transfer;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.model.User;

@Service
public class ValidateTransferAmount implements TransferValidation {

	@Override
	public void validate(Optional<User> sourceAccount, Optional<User> targetAccount, BigDecimal amountTrasnfer) {
		if(amountTrasnfer.doubleValue() <= 0.0) {
			throw new TransferException("invalid transfer amount");
		}
		
	}

}
