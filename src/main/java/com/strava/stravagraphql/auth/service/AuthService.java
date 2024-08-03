package com.strava.stravagraphql.auth.service;


import com.strava.stravagraphql.auth.dto.LoginResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    String generateToken(Authentication authentication);
    LoginResponseDto usernameAndPasswordLogin(String username, String password);
}
