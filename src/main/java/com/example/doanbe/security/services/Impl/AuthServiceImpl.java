package com.example.doanbe.security.services.Impl;

import com.example.doanbe.exception.AppException;
import com.example.doanbe.models.ERole;
import com.example.doanbe.models.Role;
import com.example.doanbe.models.User;
import com.example.doanbe.payload.request.LoginRequest;
import com.example.doanbe.payload.request.SignupRequest;
import com.example.doanbe.repository.RoleRepository;
import com.example.doanbe.repository.UserRepository;
import com.example.doanbe.security.jwt.JwtUtils;
import com.example.doanbe.security.services.AuthService;
import com.nimbusds.oauth2.sdk.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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




    @Override
    public SuccessResponse authenticate(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Map<String, Object> registerUser(SignupRequest signupRequest) {
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new AppException(400, 40, "Lỗi: User name này đã được đăng ký trước đó!");
        }

        if (userRepository.findByUsername(signupRequest.getEmail()).isPresent()) {
            throw new AppException(400, 40, "Lỗi: Email này đã được đăng ký trước đó!");
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        if (signupRequest.getRole().equals("admin")) {

        }
//        Set<String> strRoles = signupRequest.getRole();
//        Set<String> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new AppException(500, 51, "Lỗi: Không tìm thấy vai trò người dùng!"));
//            roles.add(userRole.toString());
//        } else {
//            strRoles.forEach( role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new AppException(500, 51, "Lỗi: Không tìm thấy vai trò quản trị viên!"));
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new AppException(500, 51, "Lỗi: Không tìm thấy vai trò user!"));
//                        roles.add(userRole.toString());
//                }
//            });
//
//        }



        return Map.of();
    }
}
