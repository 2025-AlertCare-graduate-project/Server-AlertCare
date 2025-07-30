package com.alertcare.server.fcm.controller.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    FCM_TOKEN_SAVE_SUCCESS(201,"FCM 토큰 등록 성공"),
    FCM_PUSH_SUCCESS(200,"푸시 전송 완료"),

    ;

    private final int status;
    private final String message;
}

