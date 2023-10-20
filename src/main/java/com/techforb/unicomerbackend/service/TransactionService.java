package com.techforb.unicomerbackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.TransferRequestDTO;
import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.repository.UserRepository;

@Service
public class TransactionService {
	
	@Autowired
	private UserRepository userRepository;

	public void makeTransfer(TransferRequestDTO transferDTO) {
		try {
			validateTransfer(transferDTO);
		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}
	}

	private void validateTransfer(TransferRequestDTO transferDTO) {
		Optional<User> sourceAccount = userRepository.findById(transferDTO.getSourceAccountId()),
		targetAccount = userRepository.findById(transferDTO.getTargetAccountId());
		
	}

}
