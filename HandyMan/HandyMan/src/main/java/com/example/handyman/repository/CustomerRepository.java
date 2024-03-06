package com.example.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.handyman.model.*;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findUserByPhoneNumber( String phNumber1);
	}

