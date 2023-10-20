package com.techforb.unicomerbackend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.unicomerbackend.model.User;
import com.techforb.unicomerbackend.model.UserTransfer;

public interface UserTransnferRepository extends JpaRepository<UserTransfer, Long> {

	Set<UserTransfer> findByUser(User x);


}
