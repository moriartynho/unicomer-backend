package com.techforb.unicomerbackend.service.validation.register;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;

@Component
public interface RegisterValidation{
	
	void validate(UserRegisterRequestDTO dto);

}
