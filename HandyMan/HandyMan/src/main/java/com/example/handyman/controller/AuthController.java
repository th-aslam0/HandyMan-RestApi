package com.example.handyman.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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
import com.example.handyman.email.EmailService;
import com.example.handyman.repository.*;
import com.example.handyman.model.*;


@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private HandyManRepository handymanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
	private EmailService senderService;
    
	@PostMapping("/login")
	public ResponseEntity<String> authenticate(@RequestBody LoginDto loginDto) {

		try {
			System.out.println("LoginDto Details: " + loginDto);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
					loginDto.getPassword());

			System.out.println("\nAuthentication Token Before Authentication: " + token);

			Authentication authResult = authenticationManager.authenticate(token);

			System.out.println("Hello");
			System.out.println("Authentication Token After Authentication: " + authResult);
			System.out.println();

			System.out.println("Authentication Token in Security Context: "
					+ SecurityContextHolder.getContext().getAuthentication());

			System.out.println();
			if (authResult.isAuthenticated())
				System.out.println("User is Authenticated");

			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Async
	public void sendEmail(String emailId, String emailSubject, String emailBody) throws MessagingException {
		senderService.sendSimpleEmail(emailId, emailBody, emailSubject);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

		try {
			System.out.println("SignUpDTO:" + signUpDto.getEmail());
			// checking for email exists in a database

			if (signUpDto.getRole().equalsIgnoreCase("handyman")) {
				Handyman user = new Handyman();
				user.setFirstName(signUpDto.getFirstName());
				user.setLastName(signUpDto.getLastName());

				user.setEmail(signUpDto.getEmail());
				user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
				String role = signUpDto.getRole();
				user.setRole(role);
				System.out.println("User: " + user.getEmail());
				handymanRepository.save(user);
				sendEmail(signUpDto.getEmail(), "Hey, " + signUpDto.getFirstName() + ",\nYour signup at Handyman is successful!", "Handyman SignUp Success");
				return new ResponseEntity<>(user, HttpStatus.CREATED);
			}

			else if (signUpDto.getRole().equalsIgnoreCase("customer")) {
				
				// creating user object
				Customer user = new Customer();
				user.setFirstName(signUpDto.getFirstName());
				user.setLastName(signUpDto.getLastName());

				user.setEmail(signUpDto.getEmail());
				user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
				String role = signUpDto.getRole();
				user.setRole(role);
				System.out.println("User: " + user.getEmail());
				customerRepository.save(user);
				return new ResponseEntity<>(user, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) {
			System.out.println("ERROR IN SIGNUP");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}