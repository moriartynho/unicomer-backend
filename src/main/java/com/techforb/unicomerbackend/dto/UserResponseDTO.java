package com.techforb.unicomerbackend.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.techforb.unicomerbackend.model.UserCard;
import com.techforb.unicomerbackend.model.UserTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

	private Long id;
	
	@NotNull(message = "name field cannot be null")
	private String username;

	private List<UserTransfer> userTransfers;

	private List<UserCard> userCards;

}
