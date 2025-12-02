package com.example.auth.javabooktdd.global.config.repository;

import com.example.auth.javabooktdd.domain.book.repository.BookInMemoryRepositoryImpl;
import com.example.auth.javabooktdd.domain.book.repository.BookRepository;
import com.example.auth.javabooktdd.domain.book.repository.BookReservationInMemoryRepository;
import com.example.auth.javabooktdd.domain.book.repository.BookReservationRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestRepositoryConfig {

    @Bean
    public BookRepository bookRepository() {
        return new BookInMemoryRepositoryImpl();
    }

    @Bean
    public BookReservationRepository bookReservationRepository() {
        return new BookReservationInMemoryRepository();
    }

}
