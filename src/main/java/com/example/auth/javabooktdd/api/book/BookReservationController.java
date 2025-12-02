package com.example.auth.javabooktdd.api.book;

import com.example.auth.javabooktdd.api.book.dto.SaveReservationRequest;
import com.example.auth.javabooktdd.applicant.book.BookReservationCancelFacade;
import com.example.auth.javabooktdd.applicant.book.BookReservationFacade;
import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.domain.book.service.BookReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book/reservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;
    private final BookReservationCancelFacade bookReservationCancelFacade;


}
