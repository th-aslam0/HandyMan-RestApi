package com.example.handyman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id")
    private long proposalId;

    @Column(name = "jobRequest_id")
    private long jobRequestId;

    @Column(name = "tradesman_name")
    private String tradesmanName;

    @Column(name = "proposal_description")
    private String proposalDescription;

    @Column(name = "proposed_Hours")
    private double proposedHours;

    @Column(name = "proposal_date")
    private Date proposalDate;

    // Constructors, getters, and setters

    public Proposal() {
        // Default constructor
    }

    public Proposal(long jobRequestId, String tradesmanName, String proposalDescription, double proposedPrice, Date proposalDate) {
        this.jobRequestId = jobRequestId;
        this.tradesmanName = tradesmanName;
        this.proposalDescription = proposalDescription;
        this.proposedHours = proposedPrice;
        this.proposalDate = proposalDate;
    }

    // Getters and setters

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public long getjobRequestId() {
        return jobRequestId;
    }

    public void setjobRequestId(long jobRequestId) {
        this.jobRequestId = jobRequestId;
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
