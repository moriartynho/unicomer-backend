package com.techforb.unicomerbackend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterRequestDTO {

	@NotNull(message = "name field cannot be null")
	private String username;
	
	@Size(min = 4, message = "login must have at least 4 characters")
	@NotNull(message = "login field cannot be null")
	private String userLogin;

	@NotNull(message = "password field cannot be null")
	@Size(min = 6, message = "password must have at least 6 characters")
	private String password;
	
	@NotNull(message = "password field cannot be null")
	@Size(min = 6, message = "confirm password must have at least 6 characters")
	private String confirmPassword;
}
