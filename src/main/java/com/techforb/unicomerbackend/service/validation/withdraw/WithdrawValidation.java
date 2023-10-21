package com.techforb.unicomerbackend.service.validation.withdraw;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;

@Component
public interface WithdrawValidation {
	
	void validate(UserTransactionRequestDTO dto);

}
