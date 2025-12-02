package com.example.auth.javabooktdd.applicant.book;

import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.domain.book.service.BookReservationService;
import com.example.auth.javabooktdd.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookReservationCancelFacade {
    private final BookReservationService bookReservationService;
    private final BookService bookService;


    @Transactional
    public BookReservationDto cancelReservation(Long id) {
        BookReservationDto bookReservationDto = bookReservationService.cancelReservation(id);
        bookService.cancelReservation(bookReservationDto.getBookId());
        return bookReservationDto;
    }
}
