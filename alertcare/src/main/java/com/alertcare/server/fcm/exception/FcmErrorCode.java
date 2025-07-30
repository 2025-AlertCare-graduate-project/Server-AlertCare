package com.alertcare.server.fcm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FcmErrorCode {
    MEMBER_FCM_NOT_FOUND(404, "사용자에게 FCM 토큰이 등록되지 않았습니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다.");

    private final int status;
    private final String message;
}
