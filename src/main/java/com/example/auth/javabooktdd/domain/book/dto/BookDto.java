package com.example.auth.javabooktdd.domain.book.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private Integer stock;
    private Boolean isReservable;
}
