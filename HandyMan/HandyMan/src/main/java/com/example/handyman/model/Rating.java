package com.example.handyman.model;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "trader_id", nullable = false)
    private int traderId;

    @Column(name = "job_request_id", nullable = false)
    private int jobRequestId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "rating_for", nullable = false)
    private String ratingFor; // Assuming it can be either "Handyman" or "Customer"

    // Constructors, getters, and setters

    public Rating() {
        // Default constructor
    }

    public Rating(int traderId, int jobRequestId, int customerId, int rating, String ratingFor) {
        this.traderId = traderId;
        this.jobRequestId = jobRequestId;
        this.customerId = customerId;
        this.rating = rating;
        this.ratingFor = ratingFor;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRatingFor() {
        return ratingFor;
    }

    public void setRatingFor(String ratingFor) {
        this.ratingFor = ratingFor;
    }
}
