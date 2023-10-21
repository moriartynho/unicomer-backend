package com.techforb.unicomerbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.unicomerbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUserLogin(String userLogin);

	Optional<User> findByUserLogin(String userLogin);

}
