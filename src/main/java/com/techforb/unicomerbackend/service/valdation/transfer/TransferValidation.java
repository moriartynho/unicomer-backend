package com.techforb.unicomerbackend.service.valdation.transfer;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.techforb.unicomerbackend.model.User;

@Component
public interface TransferValidation {

	void validate(Optional<User> sourceAccount, Optional<User> targetAccount, BigDecimal amountTrasnfer);

}
