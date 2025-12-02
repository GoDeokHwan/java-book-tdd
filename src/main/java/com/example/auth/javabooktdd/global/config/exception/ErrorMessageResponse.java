package com.example.auth.javabooktdd.global.config.exception;

import lombok.Getter;
import org.slf4j.MDC;

import java.time.LocalDateTime;

@Getter
public class ErrorMessageResponse {
    private String code;
    private String message;
    private String requestId;
    private LocalDateTime timestamp;

    public static ErrorMessageResponse error(CustomException exception) {
        ErrorMessageResponse response = new ErrorMessageResponse();
        response.code = exception.getCode();
        response.message = exception.getMessage();
        response.timestamp = LocalDateTime.now();
        response.requestId = MDC.get("traceId");
        return response;
    }

}