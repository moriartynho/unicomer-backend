package com.techforb.unicomerbackend.service.validation.deposit;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;

@Component
public interface DepositValidation {
	
	void validate(UserTransactionRequestDTO dto);

}
