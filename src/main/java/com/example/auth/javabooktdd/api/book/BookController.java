package com.example.auth.javabooktdd.api.book;

import com.example.auth.javabooktdd.api.book.dto.BookCreateRequest;
import com.example.auth.javabooktdd.api.book.dto.SaveReservationRequest;
import com.example.auth.javabooktdd.applicant.book.BookReservationFacade;
import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;
    private final BookReservationFacade bookReservationFacade;

    @PostMapping()
    public ResponseEntity<BookDto> createBook(
            @RequestBody BookCreateRequest request) {
        return ResponseEntity.ok(bookService.createBook(request.title(), request.stock()));
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookDto>> getBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(
                bookService.getTitle(title)
        );
    }

    @PostMapping("/{bookId}/reservation")
    public ResponseEntity<BookReservationDto> saveReservation(
            @PathVariable Long bookId,
            @RequestBody SaveReservationRequest request) {
        return ResponseEntity.ok(bookReservationFacade.createBookReservation(bookId,  request.userId()));
    }
}
