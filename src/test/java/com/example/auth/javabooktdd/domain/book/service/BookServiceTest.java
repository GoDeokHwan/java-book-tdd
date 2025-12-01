package com.example.auth.javabooktdd.domain.book.service;

import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.mapper.BookMapper;
import com.example.auth.javabooktdd.domain.book.mapper.BookMapperImpl;
import com.example.auth.javabooktdd.domain.book.repository.BookInMemoryRepository;
import com.example.auth.javabooktdd.domain.book.repository.BookInMemoryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        this.bookService = new BookService(
                new BookInMemoryRepositoryImpl(),
                new BookMapperImpl()
        );
    }

    @DisplayName("1. 책 생성")
    @Test
    void create_book() {
        String bookName = "책명";
        Integer stock = 10;

        BookDto book = bookService.createBook(bookName, stock);

        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(bookName);
        assertThat(book.getStock()).isEqualTo(stock);
        assertThat(book.getIsReservable()).isTrue();
    }

    

}
