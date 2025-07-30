package com.alertcare.server.fcm.controller.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum FcmMessage {
    FCM_TITLE("지금 확인해보세요"),
    FCM_BODY("낙상이 감지되었습니다."),
    ;

    private final String message;
}

