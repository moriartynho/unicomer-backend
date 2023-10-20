package com.techforb.unicomerbackend.service.validation.deposit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.DepositRequestDTO;
import com.techforb.unicomerbackend.exception.ValidateException;
import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.repository.UserRepository;

@Service
public class ValidateUserExistsToDeposit implements DepositValidation {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void validate(DepositRequestDTO dto, Optional<User> user) {
		if(!userRepository.existsById(dto.getAccountId())) {
			throw new ValidateException("user not found in our database");
		}
	}

}
