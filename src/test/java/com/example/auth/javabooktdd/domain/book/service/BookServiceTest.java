package com.example.auth.javabooktdd.domain.book.service;

import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.mapper.BookMapperImpl;
import com.example.auth.javabooktdd.domain.book.repository.BookInMemoryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
        // given
        String bookName = "책명";
        Integer stock = 10;

        // when
        BookDto book = bookService.createBook(bookName, stock);

        // then
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(bookName);
        assertThat(book.getStock()).isEqualTo(stock);
        assertThat(book.getIsReservable()).isTrue();
    }

    @DisplayName("2. 책 이름 검색")
    @Test
    void get_book_title() {
        // given
        String title = "TDD";

        // when
        List<BookDto> books = bookService.getTitle(title);

        // then
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(3);
        assertThat(books.get(0).getTitle()).contains(title);
    }

}
