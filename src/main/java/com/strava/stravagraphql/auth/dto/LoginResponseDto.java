package com.strava.stravagraphql.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String message;
    private String token;
}
