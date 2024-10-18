package com.example.doanbe.controllers;

import com.example.doanbe.models.ERole;
import com.example.doanbe.models.Role;
import com.example.doanbe.models.User;
import com.example.doanbe.payload.request.LoginRequest;
import com.example.doanbe.payload.request.SignupRequest;
import com.example.doanbe.payload.response.JwtResponse;
import com.example.doanbe.payload.response.MessageResponse;
import com.example.doanbe.repository.RoleRepository;
import com.example.doanbe.repository.UserRepository;
import com.example.doanbe.security.jwt.JwtUtils;
import com.example.doanbe.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;



}
