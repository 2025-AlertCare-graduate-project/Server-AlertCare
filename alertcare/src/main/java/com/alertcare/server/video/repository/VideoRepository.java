package com.alertcare.server.video.repository;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByUser(User user);
}
