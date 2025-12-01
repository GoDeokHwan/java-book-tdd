package com.example.auth.javabooktdd.domain.book.fixture;

import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
public class BookReservationFixture {
    private Long id;
    private Long bookId;
    private Long userId;
    private ReservationEnum status;
    private ZonedDateTime createdAt;

    public BookReservationEntity toEntity() {
        return new BookReservationEntity(id, bookId, userId, status, createdAt);
    }
}
