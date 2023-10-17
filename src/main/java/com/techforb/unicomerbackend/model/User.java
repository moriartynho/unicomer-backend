package com.techforb.unicomerbackend.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "username")
	@NotNull(message = "name field cannot be null")
	private String username;

	@Column(name = "user_password")
	@NotNull(message = "password field cannot be null")
	@Size(min = 6, message = "password must have at least 6 characters")
	private String password;

	@OneToMany
	private List<UserTransfer> userTransfers;

	@OneToMany
	private List<UserCard> userCards;

}
