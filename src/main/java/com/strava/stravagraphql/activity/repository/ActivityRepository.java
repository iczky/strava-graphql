package com.strava.stravagraphql.activity.repository;

import com.strava.stravagraphql.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("select a from Activity a join fetch a.user where a.user.id = :userId")
    Optional<Activity> findByUserId(Long userId);
}
