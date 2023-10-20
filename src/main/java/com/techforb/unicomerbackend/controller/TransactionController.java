package com.techforb.unicomerbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforb.unicomerbackend.dto.TransferRequestDTO;
import com.techforb.unicomerbackend.dto.UserResponseDTO;
import com.techforb.unicomerbackend.service.TransactionService;

@RestController
@RequestMapping(value = "/transfers")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/make-transfer")
	public ResponseEntity<UserResponseDTO> makeTransfer(@RequestBody TransferRequestDTO transferDTO){
		transactionService.makeTransfer(transferDTO);
		return ResponseEntity.ok().build();
	}

}
