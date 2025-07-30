package com.alertcare.server.video.exception;

import lombok.Getter;

@Getter
public class VideoException extends RuntimeException {
    private int status;
    private String message;

    public VideoException(VideoErrorCode errorCode) {
        super();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }
}