package com.alertcare.server.video.repository;

import com.alertcare.server.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
