package com.example.auth.javabooktdd.domain.book.repository;

import com.example.auth.javabooktdd.domain.book.fixture.BookFixture;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookInMemoryRepositoryImpl implements BookRepository {

    private static final List<BookEntity> bookEntities = new ArrayList<>();
    public BookInMemoryRepositoryImpl() {
        if (bookEntities.isEmpty()) {
            bookEntities.add(BookFixture.builder()
                    .id(1L)
                    .title("테스트 주도 개발 TDD 실천번과 도구")
                    .stock(10)
                    .isReservable(true)
                    .build()
                    .toEntity());
            bookEntities.add(BookFixture.builder()
                    .id(2L)
                    .title("Junit In Action TDD")
                    .stock(1)
                    .isReservable(false)
                    .build()
                    .toEntity());
            bookEntities.add(BookFixture.builder()
                    .id(3L)
                    .title("Clean Code TDD")
                    .stock(0)
                    .isReservable(true)
                    .build()
                    .toEntity());
        }
    }

    @Override
    public BookEntity save(BookEntity book) {
        Long nextId = bookEntities.stream()
                .mapToLong(BookEntity::getId)
                .max()
                .orElseGet(() -> 0L) + 1;

        BookEntity b = BookFixture.builder()
                .id(nextId)
                .title(book.getTitle())
                .stock(book.getStock())
                .isReservable(book.getIsReservable())
                .build()
                .toEntity();
        bookEntities.add(b);
        return b;
    }

    @Override
    public List<BookEntity> findAllByTitleContaining(String title) {
        return bookEntities;
    }

    @Override
    public Optional<BookEntity> findById(Long bookId) {
        List<BookEntity> findBook = findAllByTitleContaining(null);

        return findBook.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst();
    }


}
