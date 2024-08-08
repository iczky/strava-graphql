package com.strava.stravagraphql.activity.resolver;

import com.strava.stravagraphql.activity.dto.CreateActivityReqDto;
import com.strava.stravagraphql.activity.entity.Activity;
import com.strava.stravagraphql.activity.service.ActivityService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ActivityResolver {
    private final ActivityService activityService;
    public ActivityResolver(ActivityService activityService) {
        this.activityService = activityService;
    }

    @MutationMapping(value = "createRunActivity")
    public Activity createRunActivity(@Argument CreateActivityReqDto input){
        return activityService.createActivity(input);
    }

    @QueryMapping(value = "runActivity")
    public Activity getActivityByUserId(@Argument Long id){
        return activityService.getActivityByUserId(id);
    }

}
