package com.techforb.unicomerbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@Column(name = "username")
	@NotNull(message = "name field cannot be null")
	private String username;
	
	@Column(name="user_password")
	@NotNull(message = "password field cannot be null")
	private String password;
	
	@Column(name="user_transfers")
	private List<UserTransfer> userTransfers;

}
