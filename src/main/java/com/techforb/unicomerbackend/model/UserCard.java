package com.techforb.unicomerbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="user_cards")
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
	
	@JsonFormat(pattern = "MM/yyyy")
	@Column(name = "card_expiration_date")
	private Date cardExpirationDate;
	
	@Column(name = "card_verification_value")
	@Size(min = 3, max = 3, message = "card verification value must have 3 digits")
	@NotNull(message = "card verification value cannot be null")
	private Integer cardVerificationValue;
	
	@Column(name = "card_nickname")
	private String cardNickname;

	@ManyToOne
	private User user;
}
