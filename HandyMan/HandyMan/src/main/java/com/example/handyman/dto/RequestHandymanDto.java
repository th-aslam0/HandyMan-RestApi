package com.example.handyman.dto;

import java.time.LocalDateTime;

import com.example.handyman.model.Customer;  


public class RequestHandymanDto {
public String  jobDateTime;
public String trade;
public String jobDescription;
public int jobDuration;
public String image;




public RequestHandymanDto() {}

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
