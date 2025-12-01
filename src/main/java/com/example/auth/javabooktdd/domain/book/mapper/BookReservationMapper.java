package com.example.auth.javabooktdd.domain.book.mapper;

import com.example.auth.javabooktdd.domain.book.dto.BookReservationDto;
import com.example.auth.javabooktdd.infrastructure.book.entity.BookReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , unmappedSourcePolicy = ReportingPolicy.IGNORE
        , typeConversionPolicy = ReportingPolicy.IGNORE)
public interface BookReservationMapper {
    BookReservationDto toDto(BookReservationEntity entity);
}
