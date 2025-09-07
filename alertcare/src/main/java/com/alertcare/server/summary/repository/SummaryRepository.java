package com.alertcare.server.summary.repository;

import com.alertcare.server.summary.domain.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

}
