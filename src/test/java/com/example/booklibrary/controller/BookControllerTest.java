package com.example.booklibrary.controller;

import com.example.booklibrary.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBooks_returnsAllBooks() throws Exception {
        // GIVEN
        String expectedJson= """
                [
                    {
                        "title": "Hans im Glück",
                        "author": "Jacob Grimm",
                        "id": "1"
                    },
                    {
                        "title": "Java ist auch eine Insel",
                        "author": "Christian Ullenboom",
                        "id": "2"
                    },
                    {
                        "title": "Die besten Flachwitze für Coaches",
                        "author": "Dominic",
                        "id": "3"
                    }
                ]
                """;

        // WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void getBookById_returnsBookForGivenId() throws Exception {
        // GIVEN
        String expectedJson= """
                {
                    "title": "Hans im Glück",
                    "author": "Jacob Grimm",
                    "id": "1"
                }
                """;

        // WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }


    @Test
    void postNewBook_returns_AddedNewBook() throws Exception {
        //GIVEN
        String requestBody = """
                    {
                        "title": "Der alte Mann und das Meer",
                        "author": "Ernest Hemingway",
                        "id": "4"
                    }
                """;

        String expectedResponseBody = """
                {
                        "title": "Der alte Mann und das Meer",
                        "author": "Ernest Hemingway",
                        "id": "4"
                }
                """;

        mockMvc.perform(
                        post("/book")
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }
}