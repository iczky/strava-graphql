package com.strava.stravagraphql.user.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private Long id;
    private String username;
    private String email;
    private int age;
    private float weight;
}
