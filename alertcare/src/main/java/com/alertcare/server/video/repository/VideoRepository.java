package com.alertcare.server.video.repository;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByUser(User user);

    @Query("SELECT v FROM Video v WHERE v.isVideoAccessible = true AND v.fallDetectTime <= :threshold")
    List<Video> findVideosToDisable(@Param("threshold") LocalDateTime threshold);

}
