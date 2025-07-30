package com.alertcare.server.video.service;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.exception.UserErrorCode;
import com.alertcare.server.user.exception.UserException;
import com.alertcare.server.user.repository.UserRepository;
import com.alertcare.server.video.domain.Video;
import com.alertcare.server.video.dto.VideoCheckResponseDto;
import com.alertcare.server.video.dto.VideoListResponseDto;
import com.alertcare.server.video.dto.VideoRequestDto;
import com.alertcare.server.video.exception.VideoErrorCode;
import com.alertcare.server.video.exception.VideoException;
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
                .orElseThrow(() -> new VideoException(VideoErrorCode.VIDEO_NOT_FOUND));

        LocalDateTime fallTime = video.getFallDetectTime();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(fallTime, now);

        if (duration.toHours() >= 12) {
            throw new VideoException(VideoErrorCode.VIDEO_ACCESS_EXPIRED);
        }

        return video.getFallDetectVideoUrl();
    }

    public VideoCheckResponseDto checkVideo(Long id){
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoException(VideoErrorCode.VIDEO_NOT_FOUND));

        video.checkByUser();
        videoRepository.save(video);

        return new VideoCheckResponseDto(video.getId(), video.isCheckedByUser());
    }
}
