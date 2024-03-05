package com.example.handyman.repository;
import com.example.handyman.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByEmail( String email);
	}
