package com.example.auth.javabooktdd.domain.book.service;

import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.domain.book.mapper.BookReservationMapper;
import com.example.auth.javabooktdd.domain.book.repository.BookReservationRepository;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookReservationService {
    private final BookReservationRepository bookReservationRepository;
    private final BookReservationMapper bookReservationMapper;

    public boolean existReservationUser(Long userId) {
        return bookReservationRepository.findTopByUserIdAndStatusIn(userId, ReservationEnum.USE_STATUS)
                .isPresent();
    }

    public BookReservationDto saveBookReservation(Long bookId, Long userId) {
        BookReservationEntity bookReservation = BookReservationEntity.of(bookId, userId);
        return bookReservationMapper.toDto(
                bookReservationRepository.save(bookReservation)
        );
    }
}
