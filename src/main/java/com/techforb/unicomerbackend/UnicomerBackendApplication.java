package com.techforb.unicomerbackend;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.repository.UserRepository;

@SpringBootApplication
public class UnicomerBackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(UnicomerBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		User u1 = new User(null, "Wilkson Junior", "wilkson", "senha1234", new BigDecimal(1000.0), null, null), u2 = new User(null, "Carlos", "carlos", "senha1234",new BigDecimal(1000.0), null, null);
		Arrays.asList(u1, u2).forEach(x -> userRepository.save(x));
		
	}

}
