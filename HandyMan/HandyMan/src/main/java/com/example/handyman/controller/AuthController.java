package com.example.handyman.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.handyman.dto.LoginDto;
import com.example.handyman.dto.SignUpDto;
import com.example.handyman.repository.UserRepository;
import com.example.handyman.model.Handyman;
import com.example.handyman.model.User;

@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return new ResponseEntity<>(null, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

		try {
			System.out.println("SignUpDTO:" + signUpDto.getEmail());
			// checking for email exists in a database
			User u = userRepository.findUserByEmail(signUpDto.getEmail());
			if (signUpDto.getRole().equalsIgnoreCase("handyman")) {
				Handyman handyman = new Handyman();

				return new ResponseEntity<>(handyman, HttpStatus.CREATED);
			}

			else {
				if (u != null) {
					return new ResponseEntity<>("Email already exist!", HttpStatus.BAD_REQUEST);
				}
				// creating user object
				User user = new User();
				user.setFirstName(signUpDto.getFirstName());
				user.setLastName(signUpDto.getLastName());

				user.setEmail(signUpDto.getEmail());
				user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
				String role = signUpDto.getRole();
				user.setRole(role);
				System.out.println("User: " + user.getEmail());
				userRepository.save(user);

				return new ResponseEntity<>(user, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			System.out.println("ERROR IN SIGNUP");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}