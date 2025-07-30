package com.alertcare.server.video.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VideoErrorCode {
    VIDEO_NOT_FOUND(404,"영상이 존재하지 않습니다."),
    VIDEO_ACCESS_EXPIRED(403,"접근 권한이 만료되었습니다.");

    private final int status;
    private final String message;
}
