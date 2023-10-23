package com.techforb.unicomerbackend.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techforb.unicomerbackend.dto.CardRegisterDTO;
import com.techforb.unicomerbackend.dto.UserLoginRequestDTO;
import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;
import com.techforb.unicomerbackend.dto.UserResponseDTO;
import com.techforb.unicomerbackend.exception.ValidateException;
import com.techforb.unicomerbackend.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	@Transactional
	public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
		return ResponseEntity.ok().body(userService.findAllUsers());
	}

	@GetMapping(value = "/{id}/details")
	@Transactional
	public ResponseEntity<UserResponseDTO> findUserByUserId(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.findUserById(id));
	}

	@PostMapping(value = "/register")
	@Transactional
	public ResponseEntity<UserResponseDTO> userRegister(@RequestBody UserRegisterRequestDTO userRegisterDTO) {
		userService.userRegister(userRegisterDTO);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/login")
	@Transactional
	public ResponseEntity<UserResponseDTO> userLogin(@RequestBody UserLoginRequestDTO userLoginDTO) {
		if(!userService.existsByUserLogin(userLoginDTO.getUserLogin())) {
			throw new ValidateException("invalid user in database");
		}
		userService.userLogin(userLoginDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}/transfers")
	@Transactional
	public ResponseEntity<?> findUsersTransfersByUserId(@PathVariable Long id) {
		if (!userService.existsById(id)) {
			throw new ValidateException("invalid user in database");
		}
		return ResponseEntity.ok().body(userService.findTransfersByUserId(id));
	}

	@GetMapping(value = "/{id}/cards")
	@Transactional
	public ResponseEntity<?> finsUserCardsById(@PathVariable Long id) {
		if (!userService.existsById(id)) {
			throw new ValidateException("invalid user in database");
		}
		return ResponseEntity.ok().body(userService.findCardsByUserId(id));
	}

	@PostMapping(value = "/{id}/insert-card")
	@Transactional
	public ResponseEntity<?> insertUserCard(@PathVariable Long id, @RequestBody CardRegisterDTO cardRegisterDTO) {
		if (!userService.existsById(id)) {
			throw new ValidateException("invalid user in database");
		}
		userService.insertUserCard(id, cardRegisterDTO);
		return ResponseEntity.ok().build();
	}
}
