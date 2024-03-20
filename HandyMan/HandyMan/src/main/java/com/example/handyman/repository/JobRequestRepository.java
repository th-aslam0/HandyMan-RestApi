package com.example.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.handyman.model.JobRequest;
import com.example.handyman.model.Proposal;

public interface JobRequestRepository extends JpaRepository<JobRequest, Integer> {
	JobRequest findServiceRequestByJobId( int jobId );

}
