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

    , BOOK_ONE_RESERVATION_USER(HttpStatus.BAD_REQUEST, "책은 한권만 예약이 가능합니다.")
    , BOOK_RESERVATION_NOT_FOUD(HttpStatus.NOT_FOUND, "예약된 책 정보를 찾을 수 없습니다.")
    , BOOK_APPROVAL_RESERVATION_CANCEL_FAIL(HttpStatus.BAD_REQUEST, "승인된 책은 취소가 불가능합니다.")
    , BOOK_APPROVAL_STATUS_FAIL(HttpStatus.BAD_REQUEST, "요청 상태만 승인이 가능합니다.")
    ;

    private HttpStatus status;
    private String message;
}
