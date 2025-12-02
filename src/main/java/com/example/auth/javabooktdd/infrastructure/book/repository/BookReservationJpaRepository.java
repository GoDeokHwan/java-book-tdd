package com.example.auth.javabooktdd.infrastructure.book.repository;

import com.example.auth.javabooktdd.domain.book.repository.BookReservationRepository;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BookReservationJpaRepository extends BookReservationRepository, JpaRepository<BookReservationEntity, Long> {
    @Override
    Optional<BookReservationEntity> findTopByUserIdAndStatusIn(Long userId, Collection<ReservationEnum> status);
}
