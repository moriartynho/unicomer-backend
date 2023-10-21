package com.techforb.unicomerbackend.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.model.UserCard;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {

	Set<UserCard> findByUser(User x);

	List<UserCard> findByUserId(Long userId);


}
