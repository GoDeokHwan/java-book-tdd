package com.example.auth.javabooktdd.infrastructure.book.repository;

import com.example.auth.javabooktdd.domain.book.repository.BookRepository;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJpaRepository extends BookRepository {
    @Override
    List<BookEntity> findAllByTitleContaining(String title);
}
