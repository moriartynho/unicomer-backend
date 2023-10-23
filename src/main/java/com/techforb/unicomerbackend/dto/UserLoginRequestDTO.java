package com.techforb.unicomerbackend.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequestDTO {
	
	@NotBlank(message = "login field cannot be blank")
	private String userLogin;
	
	@NotBlank(message = "password field cannot be blank")
	private String userPassword;

}
