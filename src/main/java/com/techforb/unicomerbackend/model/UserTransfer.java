package com.techforb.unicomerbackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.techforb.unicomerbackend.model.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transfer_id")
	private Long id;

	@NotNull(message = "date field cannot be null")
	@Column(name = "transfer_date")
	private LocalDateTime transferDateTime;

	@NotNull(message = "amount field cannot be null")
	@Column(name = "trasnfer_amount")
	private BigDecimal trasnferAmount;

	@NotNull(message = "transaction type field cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
