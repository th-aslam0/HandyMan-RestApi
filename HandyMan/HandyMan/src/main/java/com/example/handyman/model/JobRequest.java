package com.example.handyman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "job_duration", nullable = false)
    private String jobDuration;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "trader_id", nullable = false)
    private Long traderId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    // Constructors, getters, and setters

    public JobRequest() {
        // Default constructor
    }

    public JobRequest(int jobId, String jobDuration, String description, double cost, Long traderId, Long customerId) {
        this.jobId= jobId;
    	this.jobDuration = jobDuration;
        this.description = description;
        this.cost = cost;
        this.traderId = traderId;
        this.customerId = customerId;
    }

    // Getters and setters

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(String jobDuration) {
        this.jobDuration = jobDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
