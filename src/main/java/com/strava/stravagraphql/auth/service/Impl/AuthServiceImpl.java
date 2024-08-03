package com.strava.stravagraphql.auth.service.Impl;

import com.strava.stravagraphql.auth.dto.LoginResponseDto;
import com.strava.stravagraphql.auth.entity.UserAuth;
import com.strava.stravagraphql.auth.service.AuthService;
import com.strava.stravagraphql.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtEncoder jwtEncoder, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().toString();

        Long userId = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found")).getId();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("strava")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("userId", userId)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }

    @Override
    public LoginResponseDto usernameAndPasswordLogin(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserAuth userDetails = (UserAuth) authentication.getPrincipal();
        String token = generateToken(authentication);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setMessage("Login successful");

        return loginResponseDto;
    }
}
