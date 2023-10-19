package com.techforb.unicomerbackend.service.valdation.register;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;
import com.techforb.unicomerbackend.repository.UserRepository;

@Component
public class ValidateUserExists implements RegisterValidation {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void validate(UserRegisterRequestDTO dto) {
		if(userRepository.existsByUserLogin(dto.getUserLogin())) {
			throw new ValidationException("user already exists in our database");
		}
	}

}
