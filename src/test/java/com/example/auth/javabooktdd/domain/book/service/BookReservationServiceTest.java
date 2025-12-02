package com.example.auth.javabooktdd.domain.book.service;


import com.example.auth.javabooktdd.applicant.book.BookReservationCancelFacade;
import com.example.auth.javabooktdd.applicant.book.BookReservationFacade;
import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import com.example.auth.javabooktdd.global.config.repository.TestRepositoryConfig;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Import(TestRepositoryConfig.class)
public class BookReservationServiceTest {
    @Autowired
    private BookReservationFacade bookReservationFacade;
    @Autowired
    private BookReservationCancelFacade bookReservationCancelFacade;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookReservationService bookReservationService;


    @DisplayName("3. 재고가 0이어서 구매 불가")
    @Test
    void unable_to_purchase_due_to_out_of_stock () {
        // given
        Long bookId = 3L;
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
        Long bookId = 99L;
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

    @DisplayName("5. 예약 성공 시 재고 감소")
    @Test
    void reservations_success () {
        // given
        Long bookId = 1L;
        Long userId = 3L;

        // when
        BookReservationDto bookReservationDto = bookReservationFacade.createBookReservation(bookId, userId);

        // then
        assertEquals(bookId, bookReservationDto.getBookId());
        assertEquals(userId, bookReservationDto.getUserId());
        assertEquals(ReservationEnum.REQUESTED, bookReservationDto.getStatus());
    }

    @DisplayName("6. 예약취소시 재고 복구")
    @Test
    void reservations_cancel() {
        // given
        Long bookId = 1L;
        Long userId = 4L;
        BookReservationDto bookReservationDto = bookReservationFacade.createBookReservation(bookId, userId);
        BookDto bookDto = bookService.getId(bookId);

        // when
        BookReservationDto cancelReservation = bookReservationCancelFacade.cancelReservation(bookReservationDto.getId());
        BookDto cancelBook = bookService.getId(bookId);

        assertEquals(bookDto.getStock() + 1, cancelBook.getStock());
        assertEquals(ReservationEnum.CANCELED, cancelReservation.getStatus());
    }

    @DisplayName("6. 예약취소시 재고 복구( 예약 정보를 못 찾았을 때)")
    @Test
    void reservations_cancel_reservation_not_found() {
        // given
        Long bookRequestId = 99L;

        // when
        CustomException exception = assertThrows(
                CustomException.class,
                () -> bookReservationCancelFacade.cancelReservation(bookRequestId));

        assertEquals(ApiExceptionEnum.BOOK_RESERVATION_NOT_FOUD.name(), exception.getCode());
    }

    @DisplayName("7. 한 번 승인된 예약은 취소 불가 ")
    @Test
    void reservations_cancel_status_approved_exception() {
        // given
        Long bookRequestId = 4L;

        // when
        CustomException exception = assertThrows(
                CustomException.class,
                () -> bookReservationCancelFacade.cancelReservation(bookRequestId));


        //then
        assertEquals(ApiExceptionEnum.BOOK_APPROVAL_RESERVATION_CANCEL_FAIL.name(), exception.getCode());
    }

    @DisplayName("7. 한 번 승인된 예약은 취소 불가( 승인하기 )")
    @Test
    void reservations_approved() {
        // given
        Long bookId = 1L;
        Long userId = 5L;
        BookReservationDto bookReservationDto = bookReservationFacade.createBookReservation(bookId, userId);

        // when
        BookReservationDto approved = bookReservationService.updateBookApproved(bookReservationDto.getId());

        assertEquals(bookReservationDto.getBookId(), approved.getBookId());
        assertEquals(ReservationEnum.APPROVED, approved.getStatus());
    }

    @DisplayName("7. 한 번 승인된 예약은 취소 불가( 승인하지만 취소되어서 오류 )")
    @Test
    void reservations_approved_status_cancel_exception() {
        // given
        Long bookId = 1L;
        Long userId = 4L;
        BookReservationDto bookReservationDto = bookReservationFacade.createBookReservation(bookId, userId);
        BookReservationDto cancelReservation = bookReservationCancelFacade.cancelReservation(bookReservationDto.getId());

        // when
        CustomException exception = assertThrows(
                CustomException.class,
                () -> bookReservationService.updateBookApproved(bookReservationDto.getId()));

        assertEquals(ApiExceptionEnum.BOOK_APPROVAL_STATUS_FAIL.name(), exception.getCode());
    }

    @DisplayName("8. Book.isReservable = false면 예약 불가")
    @Test
    void book_isReservable_false() {
        // given
        Long bookId = 2L;
        Long userId = 5L;
        // when
        CustomException exception = assertThrows(
                CustomException.class,
                () -> bookReservationFacade.createBookReservation(bookId, userId));

        assertEquals(ApiExceptionEnum.BOOK_RESERVATION_POSSIBLE.name(), exception.getCode());
    }

}
