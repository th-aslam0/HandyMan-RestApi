package com.example.handyman.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.handyman.model.Handyman;
import com.example.handyman.repository.HandyManRepository;

@Service
public class HandymanDetail implements UserDetailsService {

	@Autowired
	HandyManRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Handyman user = userRepo.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Handyman  not exists by email: " + email);
		}

		
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), null);
	}
}