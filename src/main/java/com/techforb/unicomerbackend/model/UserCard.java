package com.techforb.unicomerbackend.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private Long id;

	@CreditCardNumber
	@NotNull(message = "card number field cannot be null")
	@Column(name = "card_number")
	private String cardNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy", timezone = "GMT-3")
	@Column(name = "card_expiration_date")
	private LocalDateTime cardExpirationDate;

	@Column(name = "card_verification_value")
	@NotNull(message = "card verification value cannot be null")
	private Integer cardVerificationValue;

	@Column(name = "card_nickname")
	private String cardNickname;

	@ManyToOne
	private User user;
}
