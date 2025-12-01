package com.example.auth.javabooktdd.domain.book.repository;

import com.example.auth.javabooktdd.domain.book.fixture.BookFixture;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<BookEntity> findAllByTitleContaining(String title) {
        BookEntity b1 = BookFixture.builder()
                .id(1L)
                .title("테스트 주도 개발 TDD 실천번과 도구")
                .stock(10)
                .isReservable(true)
                .build()
                .toEntity();
        BookEntity b2 = BookFixture.builder()
                .id(2L)
                .title("Junit In Action TDD")
                .stock(0)
                .isReservable(false)
                .build()
                .toEntity();
        BookEntity b3 = BookFixture.builder()
                .id(3L)
                .title("Clean Code TDD")
                .stock(10)
                .isReservable(true)
                .build()
                .toEntity();
        return List.of(b1, b2, b3);
    }


}
