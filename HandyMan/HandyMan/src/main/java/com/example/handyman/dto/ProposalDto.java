package com.example.handyman.dto;

import java.util.Date;

public class ProposalDto {
    private int proposalId;
    private int jobRequestId;
    private String tradesmanName;
    private String proposalDescription;
    private double proposedHours;
    private Date proposalDate;

    // Constructors, getters, and setters

    public ProposalDto() {
        // Default constructor
    }

    public ProposalDto(int proposalId, int jobRequestId, String tradesmanName, String proposalDescription, double proposedHours, Date proposalDate) {
        this.proposalId = proposalId;
        this.jobRequestId = jobRequestId;
        this.tradesmanName = tradesmanName;
        this.proposalDescription = proposalDescription;
        this.proposedHours = proposedHours;
        this.proposalDate = proposalDate;
    }

    // Getters and setters

    public int getJobRequestId() {
		return jobRequestId;
	}

	public void setJobRequestId(int jobRequestId) {
		this.jobRequestId = jobRequestId;
	}

	public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
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

    public double getProposedHours() {
        return proposedHours;
    }

    public void setProposedHours(double proposedHours) {
        this.proposedHours = proposedHours;
    }

    public Date getProposalDate() {
        return proposalDate;
    }

    public void setProposalDate(Date proposalDate) {
        this.proposalDate = proposalDate;
    }
}

