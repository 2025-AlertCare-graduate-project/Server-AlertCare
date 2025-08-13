package com.alertcare.server.video.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VideoRequestDto {

    private String videoUrl;
    private String careReceiverPhoneNumber;
    private boolean fallDetected;
    private String detectedTime;

}
