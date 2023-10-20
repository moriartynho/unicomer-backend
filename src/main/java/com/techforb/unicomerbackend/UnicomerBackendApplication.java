package com.techforb.unicomerbackend;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.model.UserTransfer;
import com.techforb.unicomerbackend.model.enums.TransactionType;
import com.techforb.unicomerbackend.repository.UserRepository;
import com.techforb.unicomerbackend.repository.UserTrasnferRepository;

@SpringBootApplication
public class UnicomerBackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTrasnferRepository transnferRepository;

	public static void main(String[] args) {
		SpringApplication.run(UnicomerBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		User u1 = new User(null, "Wilkson Junior", "wilkson", "senha1234", null, null), u2 = new User(null, "Carlos", "carlos", "senha1234", null, null);
		UserTransfer ut1 = new UserTransfer(null, LocalDateTime.now(), new BigDecimal("1500.00"), TransactionType.DEPOSIT, u1), 
				ut2 = new UserTransfer(null, LocalDateTime.now(), new BigDecimal("150.00"), TransactionType.WITHDRAW, u1);
		
		Arrays.asList(u1, u2).forEach(x -> userRepository.save(x));
		Arrays.asList(ut1, ut2).forEach(x -> transnferRepository.save(x));
		
	}

}
