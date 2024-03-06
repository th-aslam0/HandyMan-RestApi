package com.example.handyman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "handymanservicerequests")
public class HandyManServiceRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = true)
	private String customerId;
	
	@Column(nullable = false)
	private String jobDateTime;
	
	@Column(nullable = false)
	private String trade;
	
	@Column(nullable = false)
	private String jobDescription;

	@Column(nullable = true)
	private int jobDuration;

	@Column(nullable = true)
	private String image;

	public HandyManServiceRequest() {}
	public HandyManServiceRequest(String customerId, String jobDateTime, String trade, String jobDescription,
			int jobDuration, String image) {
		super();
		this.customerId = customerId;
		this.jobDateTime = jobDateTime;
		this.trade = trade;
		this.jobDescription = jobDescription;
		this.jobDuration = jobDuration;
		this.image = image;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getJobDateTime() {
		return jobDateTime;
	}
	public void setJobDateTime(String jobDateTime) {
		this.jobDateTime = jobDateTime;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public int getJobDuration() {
		return jobDuration;
	}
	public void setJobDuration(int jobDuration) {
		this.jobDuration = jobDuration;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}	
}