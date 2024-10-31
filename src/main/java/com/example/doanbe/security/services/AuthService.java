package com.example.doanbe.security.services;

import com.example.doanbe.payload.request.LoginRequest;
import com.example.doanbe.payload.request.SignupRequest;
import com.nimbusds.oauth2.sdk.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface AuthService {
    SuccessResponse authenticate(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signupRequest);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
}
