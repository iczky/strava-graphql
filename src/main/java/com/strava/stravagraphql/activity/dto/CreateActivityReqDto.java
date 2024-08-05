package com.strava.stravagraphql.activity.dto;

import lombok.Data;

@Data
public class CreateActivityReqDto {
    private Long userId;
    private float distance;
    private int duration;
}
