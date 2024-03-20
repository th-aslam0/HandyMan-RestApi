package com.example.handyman.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
   
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    
    public void sendSimpleEmail(String toEmail, String subject, String body) {
    	SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom("handyman@codingpro.com.au");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }




    
}


