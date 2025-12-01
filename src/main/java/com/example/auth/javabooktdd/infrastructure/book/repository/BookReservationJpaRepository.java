package com.example.auth.javabooktdd.infrastructure.book.repository;

import com.example.auth.javabooktdd.domain.book.repository.BookReservationRepository;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BookReservationJpaRepository extends BookReservationRepository {
    @Override
    Optional<BookReservationEntity> findTopByUserIdAndStatusIn(Long userId, Collection<ReservationEnum> status);
}
