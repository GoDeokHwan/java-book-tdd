package com.example.auth.javabooktdd.api.book;

import com.example.auth.javabooktdd.api.book.dto.BookCreateRequest;
import com.example.auth.javabooktdd.domain.book.dto.BookDto;
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

    @PostMapping()
    public ResponseEntity<BookDto> createBook(BookCreateRequest request) {
        return ResponseEntity.ok(bookService.createBook(request.title(), request.stock()));
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookDto>> getBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(
                bookService.getTitle(title)
        );
    }

}
