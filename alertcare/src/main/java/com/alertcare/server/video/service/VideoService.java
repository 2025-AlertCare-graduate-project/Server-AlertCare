package com.alertcare.server.video.service;

import com.alertcare.server.video.domain.Video;
import com.alertcare.server.video.dto.VideoRequestDto;
import com.alertcare.server.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public void saveVideo(VideoRequestDto dto){
        Video video = Video.builder()
                .videoUrl(dto.getVideoUrl())
                .fallDetected(dto.isFallDetected())
                .detectedTime(dto.getDetectedTime())
                .createdAt(LocalDateTime.now())
                .build();

        videoRepository.save(video);
    }
}
