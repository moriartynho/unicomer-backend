package com.techforb.unicomerbackend.service.validation.deposit;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.DepositRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.model.User;

@Service
public class ValidateDepositAmount implements DepositValidation {

	@Override
	public void validate(DepositRequestDTO dto, Optional<User> user) {
		if (dto.getDepositAmount().doubleValue() <= 0.0) {
			throw new TransferException("invalid transfer amount");
		}
	}

}
