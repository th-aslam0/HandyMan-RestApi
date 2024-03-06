
package com.example.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.handyman.model.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {
	Proposal findServiceRequestByJobRequestId( String jobRequestId );
}



