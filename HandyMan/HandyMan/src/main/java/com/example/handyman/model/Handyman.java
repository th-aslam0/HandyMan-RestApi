package com.example.handyman.model;

import java.util.List;

import javax.persistence.Column;

public class Handyman extends User{
	@Column(nullable = false)
    private List<String> expertise;
	
	@Column(nullable = false)
    private String resAddress;
	
	@Column(nullable=false)
	private String PhNumber;
	
	@Column(nullable=false)
	private String businessAddress;
	
	@Column(nullable=false)
	private double hourlyRate;

	@Column(nullable=false)
	private String profilePicture;
	
	@Column(nullable=true)
	private String certificate;

	public List<String> getExpertise() {
		return expertise;
	}

	public void setExpertise(List<String> expertise) {
		this.expertise = expertise;
	}

	public String getResAddress() {
		return resAddress;
	}

	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}

	public String getPhNumber() {
		return PhNumber;
	}

	public void setPhNumber(String phNumber) {
		PhNumber = phNumber;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	} 
}
