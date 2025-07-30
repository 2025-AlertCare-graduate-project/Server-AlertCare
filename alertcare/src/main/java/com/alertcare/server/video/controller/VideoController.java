package com.alertcare.server.video.controller;

import com.alertcare.server.common.response.BasicResponse;
import com.alertcare.server.video.dto.VideoListResponseDto;
import com.alertcare.server.video.dto.VideoRequestDto;
import com.alertcare.server.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public String receiveVideo(@RequestBody VideoRequestDto dto){
        videoService.saveVideo(dto);
        return "영상 저장 완료";
    }

    @GetMapping("/list/{phoneNumber}")
    public BasicResponse<List<VideoListResponseDto>> getVideoList(@PathVariable String phoneNumber) {

        return BasicResponse.success(200, "비디오 리스트 조회 성공", videoService.getVideoList(phoneNumber));
    }

    @GetMapping("/{id}")
    public BasicResponse<String> getVideo(@PathVariable Long id) {
        try {
            String videoUrl = videoService.getVideoUrl(id);
            return BasicResponse.success(200, "영상 조회 성공", videoUrl);
        } catch (RuntimeException e) {
            return BasicResponse.error(403, e.getMessage());
        }
    }


}
