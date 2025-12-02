package com.example.auth.javabooktdd.applicant.book;

import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.domain.book.mapper.BookReservationMapper;
import com.example.auth.javabooktdd.domain.book.service.BookReservationService;
import com.example.auth.javabooktdd.domain.book.service.BookService;
import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookReservationFacade {
    private final BookService bookService;
    private final BookReservationService bookReservationService;
    private final BookReservationMapper bookReservationMapper;

    public BookReservationDto createBookReservation(Long bookId, Long userId) {
        // 재고 검사
        BookDto book = bookService.getId(bookId);
        if (book.getStock() <= 0) {
            throw new CustomException(ApiExceptionEnum.BOOK_ZERO_STOCK);
        }

        // 예약은 한권만 가능하다는 조건
        boolean isReservationUser = bookReservationService.existReservationUser(userId);
        if (isReservationUser) {
            throw new CustomException(ApiExceptionEnum.BOOK_ONE_RESERVATION_USER);
        }

        bookService.decreaseBook(bookId);
        return bookReservationService.saveBookReservation(bookId, userId);
    }
}
