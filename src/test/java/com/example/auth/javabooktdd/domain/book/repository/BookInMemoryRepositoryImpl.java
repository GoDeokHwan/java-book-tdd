package com.example.auth.javabooktdd.domain.book.repository;

import com.example.auth.javabooktdd.domain.book.fixture.BookFixture;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import org.springframework.stereotype.Repository;

@Repository
public class BookInMemoryRepositoryImpl implements BookInMemoryRepository {

    @Override
    public BookEntity save(BookEntity book) {
        BookEntity b = BookFixture.builder()
                .id(1L)
                .title(book.getTitle())
                .stock(book.getStock())
                .isReservable(book.getIsReservable())
                .build()
                .toEntity();
        return b;
    }


}
