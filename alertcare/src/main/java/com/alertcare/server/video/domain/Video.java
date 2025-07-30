package com.alertcare.server.video.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    private String videoUrl;

    private boolean fallDetected;

    private String detectedTime;

    private LocalDateTime createdAt;

    @Builder
    public Video(String videoUrl, boolean fallDetected, String detectedTime, LocalDateTime createdAt){
        this.videoUrl = videoUrl;
        this.fallDetected = fallDetected;
        this.detectedTime = detectedTime;
        this.createdAt = createdAt;
    }
}
