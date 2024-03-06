package com.example.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.handyman.model.HandyManServiceRequest;

public interface HandyManServiceRequestRepository extends JpaRepository<HandyManServiceRequest, Integer> {
	HandyManServiceRequest findServiceRequestBytrade( String trade );
}


