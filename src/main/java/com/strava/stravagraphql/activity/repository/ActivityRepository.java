package com.strava.stravagraphql.activity.repository;

import com.strava.stravagraphql.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
