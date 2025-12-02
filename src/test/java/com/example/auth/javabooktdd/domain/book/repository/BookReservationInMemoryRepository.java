package com.example.auth.javabooktdd.domain.book.repository;

import com.example.auth.javabooktdd.domain.book.fixture.BookReservationFixture;
import com.example.auth.javabooktdd.global.utils.date.DateUtil;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class BookReservationInMemoryRepository implements BookReservationRepository {

    private final List<BookReservationEntity> bookReservationEntities;

    public BookReservationInMemoryRepository() {
        bookReservationEntities = new ArrayList<>();
        bookReservationEntities.add(
                BookReservationFixture.builder()
                        .id(1L)
                        .userId(1L)
                        .bookId(1L)
                        .status(ReservationEnum.REQUESTED)
                        .createdAt(DateUtil.nowKst())
                        .build().toEntity()
        );
        bookReservationEntities.add(
                BookReservationFixture.builder()
                        .id(1L)
                        .userId(1L)
                        .bookId(1L)
                        .status(ReservationEnum.REQUESTED)
                        .createdAt(DateUtil.nowKst())
                        .build().toEntity()
        );
        bookReservationEntities.add(
                BookReservationFixture.builder()
                        .id(2L)
                        .userId(1L)
                        .bookId(1L)
                        .status(ReservationEnum.CANCELED)
                        .createdAt(DateUtil.nowKst())
                        .build().toEntity()
        );
        bookReservationEntities.add(
                BookReservationFixture.builder()
                        .id(3L)
                        .userId(2L)
                        .bookId(1L)
                        .status(ReservationEnum.APPROVED)
                        .createdAt(DateUtil.nowKst())
                        .build().toEntity()
        );
    }

    @Override
    public Optional<BookReservationEntity> findTopByUserIdAndStatusIn(Long userId, Collection<ReservationEnum> status) {
        return bookReservationEntities.stream()
                .filter(b -> b.getUserId().equals(userId))
                .filter(b -> status.contains(b.getStatus()))
                .findFirst();
    }

    @Override
    public BookReservationEntity save(BookReservationEntity bookReservation) {
        return BookReservationFixture.builder()
                .id(4L)
                .userId(bookReservation.getUserId())
                .bookId(bookReservation.getBookId())
                .status(bookReservation.getStatus())
                .createdAt(bookReservation.getCreatedAt())
                .build().toEntity();
    }
}
