package com.example.auth.javabooktdd.global.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private String code;
    private String message;
    private HttpStatus status;
    private ApiExceptionEnum error;

    public CustomException(ApiExceptionEnum exception) {
        this.code = exception.name();
        this.message = exception.getMessage();
        this.status = exception.getStatus();
        this.error = exception;
    }

}
