package com.alertcare.server.video.controller;

import com.alertcare.server.common.response.BasicResponse;
import com.alertcare.server.fcm.service.FcmSendService;
import com.alertcare.server.user.exception.UserErrorCode;
import com.alertcare.server.user.exception.UserException;
import com.alertcare.server.user.repository.UserRepository;
import com.alertcare.server.video.dto.VideoCheckResponseDto;
import com.alertcare.server.video.dto.VideoDetailResponseDto;
import com.alertcare.server.video.dto.VideoListResponseDto;
import com.alertcare.server.video.dto.VideoRequestDto;
import com.alertcare.server.video.exception.VideoException;
import com.alertcare.server.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final FcmSendService fcmSendService;
    private final UserRepository userRepository;

    @PostMapping
    public String receiveVideo(@RequestBody VideoRequestDto dto){
        videoService.saveVideo(dto);
        try {
            String fcmToken = userRepository.findByCareReceiverPhoneNumber(dto.getCareReceiverPhoneNumber())
                    .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND))
                    .getFcmToken();

            fcmSendService.sendMessage(fcmToken, "낙상 감지 알림", "낙상이 감지되었습니다. 확인해 주세요.");
        } catch (Exception e) {
            System.out.println("FCM 푸시 전송 실패: " + e.getMessage());
        }
        return "영상 저장 완료";
    }

    @GetMapping("/list/{phoneNumber}")
    public BasicResponse<List<VideoListResponseDto>> getVideoList(@PathVariable String phoneNumber) {

        return BasicResponse.success(200, "비디오 리스트 조회 성공", videoService.getVideoList(phoneNumber));
    }

    @GetMapping("/{id}")
    public BasicResponse<VideoDetailResponseDto> getVideoDetail(@PathVariable Long id) {

        return BasicResponse.success(200, "영상 조회 성공", videoService.getVideoDetail(id));
    }

    @PatchMapping("/{id}/check")
    public BasicResponse<VideoCheckResponseDto> checkVideo(@PathVariable Long id){

        return BasicResponse.success(200, "영상 확인 완료 처리 성공", videoService.checkVideo(id));
    }
}
