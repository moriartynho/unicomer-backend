package com.techforb.unicomerbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;
import com.techforb.unicomerbackend.dto.UserResponseDTO;
import com.techforb.unicomerbackend.service.UserService;



@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
		return ResponseEntity.ok().body(userService.findAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findUserById(@RequestParam Long id){
		return ResponseEntity.ok().body(userService.findUserById(id));
	}
	
	@PostMapping()
	public ResponseEntity<UserResponseDTO> userRegister(@RequestBody UserRegisterRequestDTO userRegisterDTO){
		userService.userRegister(userRegisterDTO);
		return ResponseEntity.ok().build();
	}
}
