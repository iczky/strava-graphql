package com.strava.stravagraphql.user.resolver;

import com.strava.stravagraphql.user.dto.CreateUserDto;
import com.strava.stravagraphql.user.dto.RegisterResponse;
import com.strava.stravagraphql.user.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserResolver {
    private final UserService userService;
    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @MutationMapping(value = "createUser")
    public RegisterResponse createUser(@Argument CreateUserDto input){
        return userService.register(input);
    }
}
