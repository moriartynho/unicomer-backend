package com.techforb.unicomerbackend.service.valdation.transfer;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.model.User;

@Component
public interface TransferValidation {

	void validate(User sourceAccount, User targetAccount);
}
