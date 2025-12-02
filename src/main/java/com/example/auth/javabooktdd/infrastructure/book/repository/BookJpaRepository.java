package com.example.auth.javabooktdd.infrastructure.book.repository;

import com.example.auth.javabooktdd.domain.book.repository.BookRepository;
import com.example.auth.javabooktdd.global.config.db.ProductionRepository;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@ProductionRepository
public interface BookJpaRepository extends BookRepository, JpaRepository<BookEntity, Long> {
    @Override
    List<BookEntity> findAllByTitleContaining(String title);
}
