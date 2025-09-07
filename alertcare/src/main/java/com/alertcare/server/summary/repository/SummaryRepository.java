package com.alertcare.server.summary.repository;

import com.alertcare.server.summary.domain.Summary;
import com.alertcare.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

}
