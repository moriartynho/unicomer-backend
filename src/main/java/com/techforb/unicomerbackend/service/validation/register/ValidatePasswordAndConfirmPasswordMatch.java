package com.techforb.unicomerbackend.service.validation.register;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;
import com.techforb.unicomerbackend.exception.ValidateException;

@Component
public class ValidatePasswordAndConfirmPasswordMatch implements RegisterValidation {

	@Override
	public void validate(UserRegisterRequestDTO dto) {
		if (!dto.getPassword().equals(dto.getConfirmPassword())) {
			throw new ValidateException("password and confirm password don't match");
		}

	}

}
