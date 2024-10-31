package com.example.doanbe.dto;

import lombok.Data;

@Data
public class VerifyOTPRequestDTO {
    private String email;
    private String otp;
}