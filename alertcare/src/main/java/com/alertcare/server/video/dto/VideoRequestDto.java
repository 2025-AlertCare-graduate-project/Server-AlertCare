package com.alertcare.server.video.dto;

import lombok.Getter;

@Getter
public class VideoRequestDto {

    private String videoUrl;
    private boolean fallDetected;
    private String detectedTime;

}
