package com.alertcare.server.activity.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ActivityRequestDto {

    private String phoneNumber;
    private String timestamp;
    private int activeTime;
    private int sittingTime;
    private int lyingTime;

}
