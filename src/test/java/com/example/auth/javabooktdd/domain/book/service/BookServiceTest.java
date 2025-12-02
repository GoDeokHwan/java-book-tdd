package com.example.auth.javabooktdd.domain.book.service;

import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.domain.book.mapper.BookMapperImpl;
import com.example.auth.javabooktdd.domain.book.repository.BookInMemoryRepositoryImpl;
import com.example.auth.javabooktdd.global.config.condition.DisabledOnTestProfile;
import com.example.auth.javabooktdd.global.config.repository.TestRepositoryConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisabledOnTestProfile("real")
@SpringBootTest
@Import(TestRepositoryConfig.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;

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
        assertThat(books.get(0).getTitle()).contains(title);
    }

}
