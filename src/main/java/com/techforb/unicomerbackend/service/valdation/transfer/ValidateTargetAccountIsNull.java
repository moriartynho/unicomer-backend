package com.techforb.unicomerbackend.service.valdation.transfer;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.model.User;

@Service
public class ValidateTargetAccountIsNull implements TransferValidation {

	@Override
	public void validate(Optional<User> sourceAccount, Optional<User> targetAccount, BigDecimal amountTrasnfer) {
		if (targetAccount.isEmpty()) {
			throw new TransferException("invalid account");
		}

	}

}
