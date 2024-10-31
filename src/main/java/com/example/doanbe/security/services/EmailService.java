package com.example.doanbe.security.services;

public interface EmailService {

    void sendOtpEmail(String to, String otpToken);

}
