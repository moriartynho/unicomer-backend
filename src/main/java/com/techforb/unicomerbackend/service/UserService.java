package com.techforb.unicomerbackend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.dto.CardRegisterDTO;
import com.techforb.unicomerbackend.dto.UserLoginRequestDTO;
import com.techforb.unicomerbackend.dto.UserRegisterRequestDTO;
import com.techforb.unicomerbackend.dto.UserResponseDTO;
import com.techforb.unicomerbackend.exception.InternalErrorException;
import com.techforb.unicomerbackend.exception.ValidateException;
import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.model.UserCard;
import com.techforb.unicomerbackend.model.UserTransfer;
import com.techforb.unicomerbackend.repository.UserCardRepository;
import com.techforb.unicomerbackend.repository.UserRepository;
import com.techforb.unicomerbackend.repository.UserTransnferRepository;
import com.techforb.unicomerbackend.service.validation.register.RegisterValidation;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTransnferRepository transnferRepository;

	@Autowired
	private UserCardRepository cardRepository;

	@Autowired
	private List<RegisterValidation> registerValidations;

	public List<UserResponseDTO> findAllUsers() {
		try {
			return this.userRepository.findAll().stream().map(x -> new UserResponseDTO(x.getId(), x.getUsername(),
					x.getBalance(), transnferRepository.findByUser(x), cardRepository.findByUser(x))).toList();
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}

	}

	public UserResponseDTO findUserById(Long id) {
		try {
			Optional<User> user = this.userRepository.findById(id);
			return new UserResponseDTO(user.get().getId(), user.get().getUsername(), user.get().getBalance(),
					user.get().getUserTransfers(), user.get().getUserCards());
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	public void userRegister(UserRegisterRequestDTO userRegisterDTO) {

		try {

			this.registerValidations.forEach(v -> v.validate(userRegisterDTO));
			User user = new User(null, userRegisterDTO.getUsername(), userRegisterDTO.getUserLogin(),
					userRegisterDTO.getPassword(), BigDecimal.ZERO, null, null);
			this.userRepository.save(user);
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}
	
	public void userLogin(UserLoginRequestDTO userLoginDTO) {
		try {
			if(!userRepository.findByUserLogin(userLoginDTO.getUserLogin()).get().getPassword().equals(userLoginDTO.getUserPassword())) {
				throw new ValidateException("incorret password");
			}
			System.out.println("login sucessful");
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	public void insertUserCard(Long userId, CardRegisterDTO cardRegisterDTO) {
		try {

			if (cardRegisterDTO.getCardExpirationDate().isBefore(LocalDateTime.now())) {
				throw new ValidateException("invalid expiration date");
			}

			User user = this.userRepository.findById(userId).get();
			UserCard card = new UserCard(null, cardRegisterDTO.getCardNumber(), cardRegisterDTO.getCardExpirationDate(),
					cardRegisterDTO.getCardVerificationValue(), cardRegisterDTO.getCardNickname(), user);
			this.cardRepository.save(card);

		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}

	}

	public List<UserCard> findCardsByUserId(Long userId) {
		try {
			return this.cardRepository.findByUserId(userId);
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	public List<UserTransfer> findTransfersByUserId(Long userId) {
		try {
			return this.transnferRepository.findByUserId(userId);
		} catch (Exception e) {
			throw new InternalErrorException(e.getMessage());
		}
	}

	public boolean existsById(Long id) {
		return this.userRepository.existsById(id);
	}

	public boolean existsByUserLogin(String userLogin) {
		return this.userRepository.existsByUserLogin(userLogin);
	}

}
