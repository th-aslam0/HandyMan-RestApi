package com.example.handyman.model;

import javax.persistence.Column;

public class Customer extends User {
	@Column(nullable = false)
    private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhNumber() {
		return PhNumber;
	}

	public void setPhNumber(String phNumber) {
		PhNumber = phNumber;
	}

	@Column(nullable=false)
	private String PhNumber;

}
