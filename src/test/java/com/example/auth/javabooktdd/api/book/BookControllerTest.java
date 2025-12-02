package com.example.auth.javabooktdd.api.book;

import com.example.auth.javabooktdd.api.book.dto.BookCreateRequest;
import com.example.auth.javabooktdd.global.config.repository.TestRepositoryConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestRepositoryConfig.class)
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("1. 책추가")
    @Test
    void create_book() throws Exception {
        // given
        BookCreateRequest request = new BookCreateRequest("Clean Code", 5);

        // when
        mockMvc.perform(
                post("/api/book")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(request.title()))
        .andExpect(jsonPath("$.stock").value(request.stock().toString()))
        .andExpect(jsonPath("$.isReservable").value(true));
    }

    @DisplayName("2. 책 제목으로 검색")
    @Test
    void get_book_title_search() throws Exception {
        // given
        String title = "TDD";

        // when
        mockMvc.perform(
                get("/api/book/title")
                        .queryParam("title", title)
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].title").value(containsString(title)));
    }

}
