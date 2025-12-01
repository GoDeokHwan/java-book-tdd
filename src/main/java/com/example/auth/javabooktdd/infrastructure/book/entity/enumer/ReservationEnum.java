package com.example.auth.javabooktdd.infrastructure.book.entity.enumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationEnum {
    REQUESTED("요청")
    , APPROVED("승인")
    , CANCELED("취소")
    ;
    private String description;
}
