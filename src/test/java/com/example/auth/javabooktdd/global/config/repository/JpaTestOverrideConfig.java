package com.example.auth.javabooktdd.global.config.repository;

import com.example.auth.javabooktdd.domain.book.repository.BookRepository;
import com.example.auth.javabooktdd.domain.book.repository.BookReservationRepository;
import com.example.auth.javabooktdd.infrastructure.book.repository.BookJpaRepository;
import com.example.auth.javabooktdd.infrastructure.book.repository.BookReservationJpaRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class JpaTestOverrideConfig {

    @Primary
    @Bean
    public BookRepository bookRepository(BookJpaRepository repo) {
        return repo;
    }

    @Primary
    @Bean
    public BookReservationRepository bookReservationRepository(BookReservationJpaRepository repo) {
        return repo;
    }
}