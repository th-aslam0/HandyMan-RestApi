package com.example.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.handyman.model.*;

public interface HandyManRepository extends JpaRepository<Handyman, Integer> {
	Handyman findyPhoneNumber( String phNumber1);
	}

