package com.techforb.unicomerbackend.service.validation.deposit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;
import com.techforb.unicomerbackend.exception.ValidateException;
import com.techforb.unicomerbackend.repository.UserRepository;

@Service
public class ValidateUserExistsToTransaction implements DepositValidation {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void validate(UserTransactionRequestDTO dto) {
		if(!userRepository.existsById(dto.getAccountId())) {
			throw new ValidateException("user not found in our database");
		}
	}

}
