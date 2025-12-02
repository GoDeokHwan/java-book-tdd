package com.example.auth.javabooktdd.infrastructure.book.entity;

import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import com.example.auth.javabooktdd.global.utils.date.DateUtil;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_reservations")
public class BookReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "user_id")
    private Long userId;
    @Enumerated(EnumType.STRING)
    private ReservationEnum status;
    @Column(name = "created_at")
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
