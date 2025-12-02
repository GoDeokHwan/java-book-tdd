package com.example.auth.javabooktdd.infrastructure.book.entity;

import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.exception.CustomException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer stock;
    @Column(name = "is_reservable")
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

    public void addByReservationCancel() {
        this.stock += 1;
    }
}
