package com.alertcare.server.user.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private int status;
    private String message;

    public UserException(UserErrorCode code) {
        super();
        this.status = code.getStatus();
        this.message = code.getMessage();
    }
}
