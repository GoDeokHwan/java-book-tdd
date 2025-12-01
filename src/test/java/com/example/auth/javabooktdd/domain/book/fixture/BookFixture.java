package com.example.auth.javabooktdd.domain.book.fixture;


import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import lombok.Builder;

@Builder
public class BookFixture {
    private Long id;
    private String title;
    private Integer stock;
    private Boolean isReservable;

    public BookEntity toEntity() {
        return new BookEntity(id, title, stock, isReservable);
    }
}
