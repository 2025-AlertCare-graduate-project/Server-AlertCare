package com.alertcare.server.fcm.exception;

import lombok.Getter;

@Getter
public class FcmException extends RuntimeException {
    private int status;
    private String message;

    public FcmException(FcmErrorCode code) {
        super();
        this.status = code.getStatus();
        this.message = code.getMessage();
    }
}
