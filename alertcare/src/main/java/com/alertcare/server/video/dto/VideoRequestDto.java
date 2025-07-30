package com.alertcare.server.video.dto;

import lombok.Getter;

@Getter
public class VideoRequestDto {

    private String videoUrl;
    private String careReceiverPhoneNumber;
    private boolean fallDetected;

}
