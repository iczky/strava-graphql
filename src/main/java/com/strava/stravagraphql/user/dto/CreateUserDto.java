package com.strava.stravagraphql.user.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
    private int age;
    private float weight;
}
