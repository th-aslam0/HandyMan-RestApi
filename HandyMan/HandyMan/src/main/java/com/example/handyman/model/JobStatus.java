package com.example.handyman.model;

import javax.persistence.*;

@Entity
public class JobStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "trader_id", nullable = false)
    private int traderId;

    @Column(name = "job_request_id", nullable = false)
    private int jobRequestId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    // Constructors, getters, and setters

    public JobStatus() {
        // Default constructor
    }

    public JobStatus(int traderId, int jobRequestId, String status, int customerId) {
        this.traderId = traderId;
        this.jobRequestId = jobRequestId;
        this.status = status;
        this.customerId = customerId;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

