package com.example.auth.javabooktdd.domain.book.service;

import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.mapper.BookMapper;
import com.example.auth.javabooktdd.domain.book.repository.BookRepository;
import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<BookDto> getTitle(String title) {
        return bookMapper.toBookDtos(
                bookRepository.findAllByTitleContaining(title)
        );
    }

    public BookDto getId(Long bookId) {
        return bookMapper.toBookDto(
                bookRepository.findById(bookId)
                        .orElseThrow(() -> new CustomException(ApiExceptionEnum.BOOK_NOT_FOUND))
        );
    }

    public void decreaseBook(Long bookId) {
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException(ApiExceptionEnum.BOOK_NOT_FOUND));

        book.decrease();
        bookRepository.save(book);
    }
}
