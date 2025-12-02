package com.example.auth.javabooktdd.domain.book.repository;

import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;

import java.util.Collection;
import java.util.Optional;

public interface BookReservationRepository {
    Optional<BookReservationEntity> findTopByUserIdAndStatusIn(Long userId, Collection<ReservationEnum> status);

    BookReservationEntity save(BookReservationEntity bookReservation);

    Optional<BookReservationEntity> findById(Long id);
}
