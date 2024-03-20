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
import com.example.handyman.model.Handyman;
import com.example.handyman.model.Customer;
import com.example.handyman.repository.*;


@SpringBootApplication
public class HandyManApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;


    
	public static void main(String[] args) {
		SpringApplication.run(HandyManApplication.class, args);
	}
	@Bean
    ApplicationRunner init1(CustomerRepository repository) {
		return args -> {
			repository.save(new Customer("John", "Doe", "johndoe@gmail.com", passwordEncoder.encode("1234qwerty"), "customer"));
			repository.save(new Customer("Aisha", "Malik", "aisha.malik@yahoo.com", passwordEncoder.encode("p@ssw0rd"), "customer"));
			repository.save(new Customer("Rahul", "Sharma", "rahul.sharma@example.com", passwordEncoder.encode("secure123"), "customer"));
			repository.save(new Customer("Emily", "Johnson", "emily.j@example.com", passwordEncoder.encode("pass123"), "customer"));			
			repository.findAll().forEach(System.out::println);
		};
	}
	
	@Bean
    ApplicationRunner init2(HandyManRepository repository) {
		return args ->{
			repository.save(new Handyman("Anu", "Malik", "anu.malik@example.com", passwordEncoder.encode("p@ssw0rd"), "handyman"));
			repository.save(new Handyman("Rohit", "Sharma", "rohit.sharma@example.com", passwordEncoder.encode("secure123"), "handyman"));
			repository.save(new Handyman("Emily", "Dickinson", "emily.dickinson@yahoo.com", passwordEncoder.encode("pass123"), "handyman"));
			repository.save(new Handyman("Alex", "Miller", "alex.m@example.com", passwordEncoder.encode("strongPass"), "handyman"));
			repository.findAll().forEach(System.out::println);
		};
		}
}
