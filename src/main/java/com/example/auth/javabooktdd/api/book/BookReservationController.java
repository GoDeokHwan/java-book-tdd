package com.example.auth.javabooktdd.api.book;

import com.example.auth.javabooktdd.api.book.dto.SaveReservationRequest;
import com.example.auth.javabooktdd.applicant.book.BookReservationCancelFacade;
import com.example.auth.javabooktdd.applicant.book.BookReservationFacade;
import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.domain.book.service.BookReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book/reservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;
    private final BookReservationCancelFacade bookReservationCancelFacade;

    @DeleteMapping("/{reservationId}/cancel")
    public ResponseEntity<Void> cancelReservation(
            @PathVariable Long reservationId
    ) {
        bookReservationCancelFacade.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{reservationId}/approved")
    public ResponseEntity<BookReservationDto> bookReservationApprove(
            @PathVariable Long reservationId
    ) {
        return ResponseEntity.ok(bookReservationService.updateBookApproved(reservationId));
    }

}
