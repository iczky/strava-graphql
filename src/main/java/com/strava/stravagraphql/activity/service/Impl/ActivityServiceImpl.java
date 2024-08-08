package com.strava.stravagraphql.activity.service.Impl;

import com.strava.stravagraphql.activity.dto.CreateActivityReqDto;
import com.strava.stravagraphql.activity.entity.Activity;
import com.strava.stravagraphql.activity.repository.ActivityRepository;
import com.strava.stravagraphql.activity.service.ActivityService;
import com.strava.stravagraphql.user.entity.User;
import com.strava.stravagraphql.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserService userService;

    public ActivityServiceImpl(ActivityRepository activityRepository, UserService userService) {
        this.activityRepository = activityRepository;
        this.userService = userService;
    }

    @Override
    public Activity createActivity(CreateActivityReqDto dto) {
        User currUser = userService.findUserById(dto.getUserId());

        Activity activity = new Activity();
        activity.setUser(currUser);
        activity.setDuration(dto.getDuration());
        activity.setDistance(dto.getDistance());

        activityRepository.save(activity);
        return activity;
    }

    @Override
    public Activity getActivityByUserId(Long userId) {
        Optional<Activity> optionalActivity = activityRepository.findByUserId(userId);

        if (optionalActivity.isEmpty()){
            throw new RuntimeException("Activity not found");
        }

        return optionalActivity.get();
    }
}
