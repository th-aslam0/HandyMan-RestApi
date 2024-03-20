package com.example.handyman.dto;

public class RatingDto {
    private int traderId;
    private int jobRequestId;
    private int customerId;
    private int rating;
    private String ratingFor;

    // Constructors, getters, and setters

    public RatingDto() {
        // Default constructor
    }

    public RatingDto(int traderId, int jobRequestId, int customerId, int rating, String ratingFor) {
        this.traderId = traderId;
        this.jobRequestId = jobRequestId;
        this.customerId = customerId;
        this.rating = rating;
        this.ratingFor = ratingFor;
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
