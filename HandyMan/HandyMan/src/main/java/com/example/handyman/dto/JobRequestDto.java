package com.example.handyman.dto;

public class JobRequestDto {
    private int jobId;
    private String jobDuration;
    private String description;
    private double cost;
    private Long traderId;
    private Long customerId;

    // Constructors, getters, and setters

    public JobRequestDto() {
        // Default constructor
    }

    public JobRequestDto(int jobId, String jobDuration, String description, double cost, Long traderId, Long customerId) {
        this.jobId = jobId;
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
