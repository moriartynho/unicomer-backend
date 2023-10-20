package com.techforb.unicomerbackend.service.validation.withdraw;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.dto.UserTransactionRequestDTO;
import com.techforb.unicomerbackend.model.User;

@Component
public interface WithdrawValidation {
	
	void validate(UserTransactionRequestDTO dto);

}
