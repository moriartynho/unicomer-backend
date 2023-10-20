package com.techforb.unicomerbackend.service.validation.deposit;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.dto.DepositRequestDTO;
import com.techforb.unicomerbackend.model.User;

@Component
public interface DepositValidation {
	
	void validate(DepositRequestDTO dto, Optional<User> user);

}
