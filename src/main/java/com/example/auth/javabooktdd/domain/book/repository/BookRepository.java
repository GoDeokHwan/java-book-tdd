package com.example.auth.javabooktdd.domain.book.repository;

import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;

import java.util.Optional;

public interface BookRepository {
    BookEntity save(BookEntity book);
}
