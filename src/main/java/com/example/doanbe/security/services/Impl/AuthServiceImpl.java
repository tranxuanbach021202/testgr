package com.example.doanbe.security.services.Impl;

import com.example.doanbe.exception.AppException;
import com.example.doanbe.entity.ERole;
import com.example.doanbe.entity.Role;
import com.example.doanbe.entity.User;
import com.example.doanbe.payload.request.LoginRequest;
import com.example.doanbe.payload.request.SignupRequest;
import com.example.doanbe.payload.response.JwtResponse;
import com.example.doanbe.payload.response.MessageResponse;
import com.example.doanbe.repository.RoleRepository;
import com.example.doanbe.repository.UserRepository;
import com.example.doanbe.security.jwt.JwtUtils;
import com.example.doanbe.security.services.AuthService;
import com.example.doanbe.security.services.EmailService;
import com.example.doanbe.security.services.UserDetailsImpl;
import com.nimbusds.oauth2.sdk.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

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

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);




    @Override
    public SuccessResponse authenticate(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new AppException(400, 40, "Lỗi: User name này đã được đăng ký trước đó!");
        }

        if (userRepository.findByUsername(signupRequest.getEmail()).isPresent()) {
            throw new AppException(400, 40, "Lỗi: Email này đã được đăng ký trước đó!");
        }
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        String strRole= signupRequest.getRole();
        Set<String> roles = new HashSet<>();


        Role role;
        logger.error("Unauthorized error: {}", strRole);
        if (strRole == null) {
            role = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new AppException(500, 51, "Lỗi: Không tìm thấy vai trò người dùng!"));
        } else {
            switch (strRole) {
                case "ROLE_ADMIN":
                    role = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found admin."));
                    break;
                default:
                    role = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found user"));
            }
        }

        user.setRole(role);

        // Tao otp
        String otp = generateOTP();
        user.setPassword(passwordEncoder.encode(otp));
        user.setOneTimePassword(otp);
        user.setVerified(false);


        userRepository.save(user);

        // Gửi OTP qua email
        emailService.sendOtpEmail("tb520385@gmail.com", otp);


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        logger.error("Unauthorized error5: {}", loginRequest.getUsername() + loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        logger.error("Unauthorized error4: {}", "aaaaa");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.error("Unauthorized error4: {}", "aaaaa");
        String jwt = jwtUtils.generateJwtToken(authentication);

        logger.error("Unauthorized error3: {}", "aaaaa");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        logger.error("Unauthorized error2: {}", "aaaaa");
        String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .findFirst()
                .orElse("ROLE_USER");

        logger.error("Unauthorized error1: {}", "aaaaa");
        return ResponseEntity.ok(
                new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        role)
        );
    }

    private String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
