package com.techforb.unicomerbackend.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequestDTO {

	@NotNull(message = "source account cannot be null")
	private Long sourceAccountId;
	
	@NotNull(message = "account account cannot be null")
	private Long targetAccountId;
	
	@NotNull(message = "transfer amount cannot be null")
	private BigDecimal transferAmount;
}
