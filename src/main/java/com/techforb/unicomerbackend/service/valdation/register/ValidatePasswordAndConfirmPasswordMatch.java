package com.techforb.unicomerbackend.service.valdation.register;

import javax.validation.ValidationException;

import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;

public class ValidatePasswordAndConfirmPasswordMatch implements RegisterValidation {

	@Override
	public void validate(UserRegisterRequestDTO dto) {
		if (!dto.getPassword().equals(dto.getConfirmPassword())) {
			throw new ValidationException("password and confirm password don't match");
		}

	}

}
