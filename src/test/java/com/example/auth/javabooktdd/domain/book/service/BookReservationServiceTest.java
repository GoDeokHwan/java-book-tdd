package com.example.auth.javabooktdd.domain.book.service;


import com.example.auth.javabooktdd.applicant.book.BookReservationFacade;
import com.example.auth.javabooktdd.domain.book.mapper.BookMapperImpl;
import com.example.auth.javabooktdd.domain.book.mapper.BookReservationMapper;
import com.example.auth.javabooktdd.domain.book.mapper.BookReservationMapperImpl;
import com.example.auth.javabooktdd.domain.book.repository.BookInMemoryRepositoryImpl;
import com.example.auth.javabooktdd.domain.book.repository.BookReservationInMemoryRepository;
import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BookReservationServiceTest {
    private BookReservationFacade bookReservationFacade;

    @BeforeEach
    void setUp() {
        this.bookReservationFacade = new BookReservationFacade(
                new BookService(
                        new BookInMemoryRepositoryImpl(),
                        new BookMapperImpl()
                ),
                new BookReservationService(
                        new BookReservationInMemoryRepository()
                ),
                new BookReservationMapperImpl()
        );
    }

    @DisplayName("3. 재고가 0이어서 구매 불가")
    @Test
    void unable_to_purchase_due_to_out_of_stock () {
        // given
        Long bookId = 2L;
        Long userId = 1L;

        // when
        CustomException exception = assertThrows(
                CustomException.class,
                () -> bookReservationFacade.createBookReservation(bookId, userId)
        );

        // then
        assertEquals(ApiExceptionEnum.BOOK_ZERO_STOCK.name(), exception.getCode());
    }

    @DisplayName("3. 책을 못 찾아서 구매 불가")
    @Test
    void not_found_book_out_of_stock () {
        // given
        Long bookId = 4L;
        Long userId = 1L;
        // when
        CustomException exception = assertThrows(
                CustomException.class,
                () -> bookReservationFacade.createBookReservation(bookId, userId)
        );

        // then
        assertEquals(ApiExceptionEnum.BOOK_NOT_FOUND.name(), exception.getCode());
    }

    @DisplayName("4. 이미 예약한 사람이 또 예약하면 안 됨 ")
    @Test
    void reservations_are_limited_to_one_ticket () {
        // given
        Long bookId = 1L;
        Long userId = 1L;
        // when
        CustomException exception = assertThrows(
                CustomException.class,
                () -> bookReservationFacade.createBookReservation(bookId, userId)
        );

        // then
        assertEquals(ApiExceptionEnum.BOOK_ONE_RESERVATION_USER.name(), exception.getCode());
    }

}
