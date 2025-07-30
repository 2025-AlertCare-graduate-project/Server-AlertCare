package com.alertcare.server.common.exception;

import com.alertcare.server.common.response.BasicResponse;
import com.alertcare.server.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<BasicResponse<Void>> handleUserException(UserException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(BasicResponse.error(ex.getStatus(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BasicResponse<Void>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BasicResponse.error(500, ex.toString()));
    }
}
