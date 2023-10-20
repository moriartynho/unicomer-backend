package com.techforb.unicomerbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequestDTO {

	private Long sourceAccountId;
	private Long targetAccountId;
}
