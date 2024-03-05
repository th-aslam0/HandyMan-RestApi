package com.example.handyman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.handyman.model.User;
import com.example.handyman.repository.UserRepository;

@SpringBootApplication
public class HandyManApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HandyManApplication.class, args);
	}
	@Bean
    ApplicationRunner init(UserRepository repository) {
		return args -> {
			repository.save( new User ("a","b", "c", passwordEncoder.encode("1234"), "d" ));
			repository.findAll().forEach(System.out::println);

		};
	}
}
