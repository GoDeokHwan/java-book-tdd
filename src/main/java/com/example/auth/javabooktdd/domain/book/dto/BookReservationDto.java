package com.example.auth.javabooktdd.domain.book.dto;

import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookReservationDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private ReservationEnum status;
    private ZonedDateTime createdAt;

}
