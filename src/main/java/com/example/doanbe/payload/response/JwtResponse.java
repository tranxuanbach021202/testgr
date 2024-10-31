package com.example.doanbe.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String role;

    public JwtResponse(String accessToken, Long id, String username, String email,
                       String role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
    }


}