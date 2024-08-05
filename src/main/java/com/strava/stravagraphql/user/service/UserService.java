package com.strava.stravagraphql.user.service;

import com.strava.stravagraphql.user.dto.CreateUserDto;
import com.strava.stravagraphql.user.dto.RegisterResponse;
import com.strava.stravagraphql.user.entity.User;

import java.util.Optional;

public interface UserService {
    User findUserById(Long id);
    User findByUsername(String email);
    RegisterResponse register(CreateUserDto dto);
}
