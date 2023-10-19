package com.techforb.unicomerbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;
import com.techforb.unicomerbackend.dto.UserResponseDTO;
import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.repository.UserRepository;
import com.techforb.unicomerbackend.service.valdation.register.RegisterValidation;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private List<RegisterValidation> registerValidations;

	public List<UserResponseDTO> findAllUsers() {
		try {
			return this.userRepository.findAll().stream()
					.map(x -> new UserResponseDTO(x.getId(), x.getUsername(), x.getUserTransfers(), x.getUserCards()))
					.toList();
		} catch (Exception e) {
			throw new InternalError("an internal error occurred when trying to access the database");
		}

	}

	public UserResponseDTO findUserById(Long id) {
		try {
			Optional<User> user = this.userRepository.findById(id);
			return new UserResponseDTO(user.get().getId(), user.get().getUsername(), user.get().getUserTransfers(),
					user.get().getUserCards());
		} catch (Exception e) {
			throw new InternalError("an internal error occurred when trying to access the database");
		}
	}

	public void userRegister(UserRegisterRequestDTO userRegisterDTO) {
		this.registerValidations.forEach(v -> v.validate(userRegisterDTO));

	}

}