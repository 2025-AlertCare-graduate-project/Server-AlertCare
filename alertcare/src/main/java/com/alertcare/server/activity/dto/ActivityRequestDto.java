package com.alertcare.server.activity.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VideoRequestDto {

    private String phoneNumber;
    private LocalDateTime timestamp;
    private int activeTime;
    private int sittingTime;
    private int lyingTime;

}
