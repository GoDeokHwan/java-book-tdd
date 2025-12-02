package com.example.auth.javabooktdd.api.book;

import com.example.auth.javabooktdd.api.book.dto.BookCreateRequest;
import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @PostMapping()
    public ResponseEntity<BookDto> createBook(BookCreateRequest request) {
        return ResponseEntity.ok(bookService.createBook(request.title(), request.stock()));
    }

}
