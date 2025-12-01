package com.example.auth.javabooktdd.domain.book.mapper;

import com.example.auth.javabooktdd.domain.book.dto.BookDto;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , unmappedSourcePolicy = ReportingPolicy.IGNORE
        , typeConversionPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    BookDto toBookDto(BookEntity entity);
}
