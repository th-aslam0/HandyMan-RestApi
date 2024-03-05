package com.example.handyman.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.handyman.model.*;
import com.example.handyman.model.User;
import com.example.handyman.repository.UserRepository;

@Service
public class UserDetails implements UserDetailsService {
@Autowired    
UserRepository userRepo;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username);
//        if(user==null){
//            throw new UsernameNotFoundException("User not exists by Username");
//        }
//           
//        String<GrantedAuthority> authorities = user.getRole()
//                .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
        return null;
    }
}