package com.techforb.unicomerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.unicomerbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
