package com.example.auth.javabooktdd.domain.book.service;

import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.mapper.BookMapper;
import com.example.auth.javabooktdd.domain.book.repository.BookRepository;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto createBook(String title, Integer stock) {
        BookEntity book = BookEntity.of(title, stock);
        return bookMapper.toBookDto(
                bookRepository.save(book)
        );
    }
}
