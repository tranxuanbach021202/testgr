package com.example.doanbe.security.services;

import com.example.doanbe.payload.request.LoginRequest;
import com.example.doanbe.payload.request.SignupRequest;
import com.nimbusds.oauth2.sdk.SuccessResponse;

import java.util.Map;

public interface AuthService {
    SuccessResponse authenticate(LoginRequest loginRequest);

    Map<String, Object> registerUser(SignupRequest signupRequest);
}
