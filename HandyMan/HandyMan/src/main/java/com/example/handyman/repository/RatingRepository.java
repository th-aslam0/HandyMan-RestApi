package com.example.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.handyman.model.JobStatus;
import com.example.handyman.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
	Rating findServiceRequestByJobRequestId( int jobRequestId );
}