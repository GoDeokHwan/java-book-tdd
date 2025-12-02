package com.example.auth.javabooktdd.api.book;

import com.example.auth.javabooktdd.api.book.dto.SaveReservationRequest;
import com.example.auth.javabooktdd.global.config.condition.DisabledOnTestProfile;
import com.example.auth.javabooktdd.global.config.exception.ApiExceptionEnum;
import com.example.auth.javabooktdd.global.config.repository.TestRepositoryConfig;
import com.example.auth.javabooktdd.infrastructure.book.entity.enumer.ReservationEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisabledOnTestProfile("real")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestRepositoryConfig.class)
class BookReservationControllerTest {
    @Autowired
    MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("책 예약")
    @Test
    void book_reservation () throws Exception {
        // when
        SaveReservationRequest request = new SaveReservationRequest( 7L);

        // given
        mockMvc.perform(
                post("/api/book/1/reservation")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(request.userId()))
        .andExpect(jsonPath("$.status").value(ReservationEnum.REQUESTED.name()))
        ;
    }

    @DisplayName("책 예약 중복 실패")
    @Test
    void book_reservation_duplicate () throws Exception {
        // when
        SaveReservationRequest request = new SaveReservationRequest( 8L);
        mockMvc.perform(
                        post("/api/book/1/reservation")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
        ;

        // given
        mockMvc.perform(
                        post("/api/book/1/reservation")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message")
                        .value(ApiExceptionEnum.BOOK_ONE_RESERVATION_USER.getMessage())
                )
        ;
    }

    @DisplayName("예약 취소")
    @Test
    void cancel_reservation () throws Exception {
        // given
        Long reservationId = 1L;

        // when
        mockMvc.perform(
                delete(String.format("/api/book/reservation/%d/cancel", reservationId))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @DisplayName("승인")
    @Test
    void approve_reservation () throws Exception {
        // given
        Long reservationId = 2L;

        // when
        mockMvc.perform(
                        patch(String.format("/api/book/reservation/%d/approved", reservationId))
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(reservationId))
        .andExpect(jsonPath("$.status").value(ReservationEnum.APPROVED.name()));
    }

}