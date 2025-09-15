package com.alertcare.server.summary.repository;

import com.alertcare.server.summary.domain.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
    Optional<Summary> findByUserIdAndDate(
            @Param("userId") Long userId,
            @Param("date") LocalDate date
    );
}
