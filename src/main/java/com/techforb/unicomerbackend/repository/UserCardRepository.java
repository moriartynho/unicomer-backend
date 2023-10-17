package com.techforb.unicomerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.unicomerbackend.model.UserCard;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {

}
