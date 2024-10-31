package com.example.doanbe.security.services.Impl;

import com.example.doanbe.security.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String to, String otpToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tempab102@gmail.com");
        message.setTo(to);
        message.setSubject("Your OTP");
        message.setText("Your OTP has been sent to " + otpToken + "this OTP will expire in 5 minutes");

        mailSender.send(message);
    }
}
