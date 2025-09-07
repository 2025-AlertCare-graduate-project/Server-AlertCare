package com.alertcare.server.activity.repository;

import com.alertcare.server.activity.domain.Activity;
import com.alertcare.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT COALESCE(SUM(a.activeTime), 0), " +
            "       COALESCE(SUM(a.sittingTime), 0), " +
            "       COALESCE(SUM(a.lyingTime), 0) " +
            "FROM Activity a " +
            "WHERE a.user.id = :userId " +
            "AND a.timestamp >= :start " +
            "AND a.timestamp <= :end")
    Object sumByDateAndUser(@Param("userId") Long userId,
                            @Param("start") LocalDateTime start,
                            @Param("end") LocalDateTime end);

}
