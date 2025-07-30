package com.alertcare.server.video.service;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.exception.UserErrorCode;
import com.alertcare.server.user.exception.UserException;
import com.alertcare.server.user.repository.UserRepository;
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
}
