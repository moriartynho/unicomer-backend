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
	@Transactional
	public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
		return ResponseEntity.ok().body(userService.findAllUsers());
	}
	
	@GetMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findUserById(id));
	}
	
	@PostMapping()
	public ResponseEntity<UserResponseDTO> userRegister(@RequestBody UserRegisterRequestDTO userRegisterDTO){
		userService.userRegister(userRegisterDTO);
		return ResponseEntity.ok().build();
	}
}
