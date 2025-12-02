package com.example.auth.javabooktdd.infrastructure.book.entity;

import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import com.example.auth.javabooktdd.global.utils.date.DateUtil;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookReservationEntity {
    private Long id;
    private Long bookId;
    private Long userId;
    private ReservationEnum status;
    private ZonedDateTime createdAt;

    public static BookReservationEntity of(Long bookId, Long userId) {
        BookReservationEntity instance = new BookReservationEntity();
        instance.bookId = bookId;
        instance.userId = userId;
        instance.status = ReservationEnum.REQUESTED;
        instance.createdAt = DateUtil.nowKst();
        return instance;
    }

    public void cancel() {
        if (this.isApproved()) {
            throw new CustomException(ApiExceptionEnum.BOOK_APPROVAL_RESERVATION_CANCEL_FAIL);
        }
        this.status = ReservationEnum.CANCELED;
    }

    private boolean isApproved() {
        return this.getStatus().isApproved();
    }

    public void approved() {
        if (!this.isRequested()) {
            throw new CustomException(ApiExceptionEnum.BOOK_APPROVAL_STATUS_FAIL);
        }
        this.status = ReservationEnum.APPROVED;
    }

    private boolean isRequested() {
        return this.getStatus().isRequested();
    }
}
