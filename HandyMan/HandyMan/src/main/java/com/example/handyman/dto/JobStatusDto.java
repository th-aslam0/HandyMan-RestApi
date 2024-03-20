package com.example.handyman.dto;

public class JobStatusDto {
    private int traderId;
    private int jobRequestId;
    private String status;
    private int customerId;

    // Constructors, getters, and setters

    public JobStatusDto() {
        // Default constructor
    }

    public JobStatusDto(int traderId, int jobRequestId, String status, int customerId) {
        this.traderId = traderId;
        this.jobRequestId = jobRequestId;
        this.status = status;
        this.customerId = customerId;
    }

    // Getters and setters

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public int getJobRequestId() {
        return jobRequestId;
    }

    public void setJobRequestId(int jobRequestId) {
        this.jobRequestId = jobRequestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
