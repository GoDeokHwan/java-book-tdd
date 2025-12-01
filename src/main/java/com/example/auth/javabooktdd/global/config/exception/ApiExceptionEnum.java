package com.example.auth.javabooktdd.global.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiExceptionEnum {
    SUCCESS(HttpStatus.OK, "success")

    , BOOK_ZERO_STOCK(HttpStatus.BAD_REQUEST, "재고가 없습니다.")
    , BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "책을 찾을 수 없습니다.")

    ;

    private HttpStatus status;
    private String message;
}
