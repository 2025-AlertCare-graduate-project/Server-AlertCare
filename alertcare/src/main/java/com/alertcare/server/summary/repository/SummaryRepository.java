package com.alertcare.server.summary.repository;

import com.alertcare.server.summary.domain.Summary;
import com.alertcare.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

    @Query("SELECT COALESCE(SUM(r.activeTime), 0), " + //sum값 null인 경우 0으로 저장
            "       COALESCE(SUM(r.sittingTime), 0), " +
            "       COALESCE(SUM(r.lyingTime), 0) " +
            "FROM Record r " +
            "WHERE DATE(r.timestamp) = :date " +
            "AND r.user = :user")
    Object[] sumByDateAndUser(@Param("date") LocalDate date,
                              @Param("user") User user);
}
