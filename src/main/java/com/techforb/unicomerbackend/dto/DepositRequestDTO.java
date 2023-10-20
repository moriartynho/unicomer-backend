package com.techforb.unicomerbackend.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositRequestDTO {
	
	@NotNull(message = "id cannot be null")
	private Long accountId;
	
	@NotNull(message = "amount cannot be null")
	private BigDecimal depositAmount;

}
