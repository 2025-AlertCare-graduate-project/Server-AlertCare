package com.alertcare.server.video.service;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.exception.UserErrorCode;
import com.alertcare.server.user.exception.UserException;
import com.alertcare.server.user.repository.UserRepository;
import com.alertcare.server.video.domain.Video;
import com.alertcare.server.video.dto.VideoListResponseDto;
import com.alertcare.server.video.dto.VideoRequestDto;
import com.alertcare.server.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public void saveVideo(VideoRequestDto dto) {
        User user = userRepository.findByCareReceiverPhoneNumber(dto.getCareReceiverPhoneNumber().trim())
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        Video video = Video.builder()
                .fallDetectVideoUrl(dto.getVideoUrl())
                .user(user)
                .fallDetectTime(LocalDateTime.now())
                .build();

        videoRepository.save(video);
    }

    public List<VideoListResponseDto> getVideoList() {
        List<Video> videoList = videoRepository.findAll();

        return videoList.stream()
                .map(video -> {
                    // 48시간 지나면 만료
                    boolean isExpired = video.getFallDetectTime().plusHours(48).isBefore(LocalDateTime.now());

                    return new VideoListResponseDto(
                            video.getFallDetectTime(),
                            video.getFallDetectVideoUrl(),
                            isExpired
                    );
                })
                .collect(Collectors.toList());
    }
}
