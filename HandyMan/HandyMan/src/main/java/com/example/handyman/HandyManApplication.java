package com.example.handyman;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.handyman.email.EmailService;
import com.example.handyman.model.Customer;
import com.example.handyman.model.Customer;
import com.example.handyman.repository.*;


@SpringBootApplication
public class HandyManApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
	private EmailService senderService;
    
	public static void main(String[] args) {
		SpringApplication.run(HandyManApplication.class, args);
	}
	@Bean
    ApplicationRunner init(CustomerRepository repository) {
		return args -> {
			repository.save(new Customer ("a","b", "abc@gmail.com", passwordEncoder.encode("12345"), "d" ));
			repository.findAll().forEach(System.out::println);
		};
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//		senderService.sendSimpleEmail("taimoor.h.aslam@gmail.com",
//				"This is email body",
//				"This is email subject");
//
//	}
//	@Bean
//    ApplicationRunner init(HandyManRepository repository) {
//		return args ->{};
//
//		}
}
