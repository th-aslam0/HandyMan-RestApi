package com.example.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.handyman.model.JobRequest;
import com.example.handyman.model.JobStatus;

public interface JobStatusRepository extends JpaRepository<JobStatus, Integer> {
	JobStatus findServiceRequestByJobRequestId( int jobRequestId );
}
