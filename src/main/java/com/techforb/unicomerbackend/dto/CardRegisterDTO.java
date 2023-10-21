package com.techforb.unicomerbackend.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardRegisterDTO {

	@NotNull(message = "card number field cannot be null")
	private String cardNumber;

	@Column(name = "card_expiration_date")
	private LocalDateTime cardExpirationDate;

	@NotNull(message = "card verification value cannot be null")
	private Integer cardVerificationValue;

	private String cardNickname;
}
