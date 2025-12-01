package com.example.auth.javabooktdd.domain.book.service;

import com.example.auth.javabooktdd.domain.book.repository.BookReservationRepository;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookReservationService {
    private final BookReservationRepository bookReservationRepository;

    public boolean existReservationUser(Long userId) {
        return bookReservationRepository.findTopByUserIdAndStatusIn(userId, ReservationEnum.USE_STATUS)
                .isPresent();
    }
}
