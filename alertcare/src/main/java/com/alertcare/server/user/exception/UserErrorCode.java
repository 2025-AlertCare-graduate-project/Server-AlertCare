package com.alertcare.server.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode {
    MEMBER_DUPLICATED(400, "이미 존재하는 회원입니다."),
    PHONE_NUMBER_DUPLICATED(400, "사용 중인 전화번호입니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다.");

    private final int status;
    private final String message;
}
