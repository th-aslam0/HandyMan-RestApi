package com.example.handyman.dto;

import java.util.Date;

public class ProposalDto {
    private long proposalId;
    private long jobRequestId;
    private String tradesmanName;
    private String proposalDescription;
    private double proposedHours;
    private Date proposalDate;

    // Constructors, getters, and setters

    public ProposalDto() {
        // Default constructor
    }

    public ProposalDto(long proposalId, long jobRequestId, String tradesmanName, String proposalDescription, double proposedHours, Date proposalDate) {
        this.proposalId = proposalId;
        this.jobRequestId = jobRequestId;
        this.tradesmanName = tradesmanName;
        this.proposalDescription = proposalDescription;
        this.proposedHours = proposedHours;
        this.proposalDate = proposalDate;
    }

    // Getters and setters

    public long getJobRequestId() {
		return jobRequestId;
	}

	public void setJobRequestId(long jobRequestId) {
		this.jobRequestId = jobRequestId;
	}

	public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public String getTradesmanName() {
        return tradesmanName;
    }

    public void setTradesmanName(String tradesmanName) {
        this.tradesmanName = tradesmanName;
    }

    public String getProposalDescription() {
        return proposalDescription;
    }

    public void setProposalDescription(String proposalDescription) {
        this.proposalDescription = proposalDescription;
    }

    public double getproposedHours() {
        return proposedHours;
    }

    public void setproposedHours(double proposedHours) {
        this.proposedHours = proposedHours;
    }

    public Date getProposalDate() {
        return proposalDate;
    }

    public void setProposalDate(Date proposalDate) {
        this.proposalDate = proposalDate;
    }
}

