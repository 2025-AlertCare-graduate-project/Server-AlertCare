package com.alertcare.server.common.response;

import com.alertcare.server.fcm.controller.enums.ResponseMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BasicResponse<T> {
    private String status; // "success" or "error"
    private int code;      // HTTP 상태 코드
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> BasicResponse<T> success(int code, String message, T data) {
        return BasicResponse.<T>builder()
                .status("success")
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    // ResponseMessage 기반 success 메서드
    public static <T> BasicResponse<T> success(ResponseMessage responseMessage, T data) {
        return BasicResponse.<T>builder()
                .status("success")
                .code(responseMessage.getStatus())
                .message(responseMessage.getMessage())
                .data(data)
                .build();
    }

    public static <T> BasicResponse<T> error(int code, String message) {
        return BasicResponse.<T>builder()
                .status("error")
                .code(code)
                .message(message)
                .build();
    }
}
