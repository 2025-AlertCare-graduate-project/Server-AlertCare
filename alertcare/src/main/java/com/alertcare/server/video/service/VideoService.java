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

import java.time.Duration;
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

    public List<VideoListResponseDto> getVideoList(String phoneNumber) {
        User user = userRepository.findByCareReceiverPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        List<Video> videoList = videoRepository.findByUser(user);

        return videoList.stream()
                .map(video -> {
                    return new VideoListResponseDto(
                            video.getId(),
                            video.getFallDetectTime(),
                            video.isVideoAccessible(),
                            video.isCheckedByUser()
                    );
                })
                .collect(Collectors.toList());
    }

    public String getVideoUrl(Long id){
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("영상이 존재하지 않습니다."));

        LocalDateTime fallTime = video.getFallDetectTime();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(fallTime, now);

        System.out.println("===== Video 조회 요청 =====");
        System.out.println("현재 시간: " + now);
        System.out.println("fallDetectTime: " + fallTime);
        System.out.println("두 시간 차이(시간 단위): " + duration.toHours());


        if (duration.toHours() >= 12) {
            throw new RuntimeException("접근 권한이 만료되었습니다.");
        }

        return video.getFallDetectVideoUrl();
    }
}
