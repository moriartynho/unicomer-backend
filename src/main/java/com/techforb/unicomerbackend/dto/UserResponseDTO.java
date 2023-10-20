package com.techforb.unicomerbackend.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

	private Long id;

	@NotNull(message = "name field cannot be null")
	private String username;

}
