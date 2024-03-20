package com.example.handyman.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.handyman.model.User;
import com.example.handyman.repository.UserRepository;

@Service
public class UserDetail implements UserDetailsService {
	
@Autowired    
UserRepository userRepo;

@Override
public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
    User user = userRepo.findUserByEmail(email);
    if(user==null){
        throw new UsernameNotFoundException("User not exists by Username");
    }
       
    List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
    authority.add( new SimpleGrantedAuthority("ROLE_USER"));
    return new org.springframework.security.core.userdetails.User(email,user.getPassword(),authority);
}
}