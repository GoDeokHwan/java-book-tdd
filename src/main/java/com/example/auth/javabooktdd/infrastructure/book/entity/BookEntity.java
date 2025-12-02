package com.example.auth.javabooktdd.infrastructure.book.entity;

import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookEntity {
    private Long id;
    private String title;
    private Integer stock;
    private Boolean isReservable;

    public static BookEntity of(String title, Integer stock) {
        BookEntity book = new BookEntity();
        book.title = title;
        book.stock = stock;
        book.isReservable = true;
        return book;
    }

    public void decrease() {
        if (this.getStock() < 1) {
            throw new CustomException(ApiExceptionEnum.BOOK_ZERO_STOCK);
        }
        this.stock -= 1;
    }
}
