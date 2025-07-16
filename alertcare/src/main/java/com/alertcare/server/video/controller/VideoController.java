package com.alertcare.server.video.controller;

import com.alertcare.server.video.dto.VideoRequestDto;
import com.alertcare.server.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public String receiveVideo(@RequestBody VideoRequestDto dto){
        videoService.saveVideo(dto);
        return "영상 저장 완료";
    }
}
