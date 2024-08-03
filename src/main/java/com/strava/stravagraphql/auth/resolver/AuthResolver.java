package com.strava.stravagraphql.auth.resolver;

import com.strava.stravagraphql.auth.dto.LoginResponseDto;
import com.strava.stravagraphql.auth.service.AuthService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthResolver {
    private final AuthService authService;
    public AuthResolver(AuthService authService) {
        this.authService = authService;
    }

    @MutationMapping(value = "login")
    public LoginResponseDto login(@Argument String username, @Argument String password){
        return authService.usernameAndPasswordLogin(username, password);
    }
}
