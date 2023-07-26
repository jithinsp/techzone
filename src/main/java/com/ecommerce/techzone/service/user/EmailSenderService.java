package com.ecommerce.techzone.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail,
                         String subject,
                         String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jithinsp4@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("OTP Send successfully");
    }
}
