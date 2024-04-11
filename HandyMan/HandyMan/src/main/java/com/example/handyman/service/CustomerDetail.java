package com.example.handyman.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.handyman.model.Customer;
import com.example.handyman.repository.CustomerRepository;

@Service
public class CustomerDetail implements UserDetailsService {

	
	CustomerRepository userRepo;

	@Autowired
	public CustomerDetail( CustomerRepository userRepo ) { this.userRepo = userRepo; }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer user = userRepo.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Customer not exists by email: " + email);
		}

	
		return (UserDetails) user;
	}
}