package com.techforb.unicomerbackend.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
	
	@NotBlank(message = "login field cannot be blank")
	private String userLogin;
	
	@NotBlank(message = "password field cannot be blank")
	private String userPassword;

}
