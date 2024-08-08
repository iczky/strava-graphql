package com.strava.stravagraphql.activity.service;

import com.strava.stravagraphql.activity.dto.CreateActivityReqDto;
import com.strava.stravagraphql.activity.entity.Activity;

public interface ActivityService {
    Activity createActivity(CreateActivityReqDto dto);
    Activity getActivityByUserId(Long userId);
}
