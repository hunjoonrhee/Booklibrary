package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookDB;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    BookDB bookDB = mock(BookDB.class);
    BookService bookService = new BookService(bookDB);
    @Test
    void getAllBooks_ShouldReturn_AllBooks(){
        // GIVEN
        List<Book> books = new ArrayList<>();
        books.add(new Book("Hans im Glück", "Jacob Grimm", "1"));
        books.add(new Book("Die besten Flachwitze für Coaches", "Dominic", "3"));

        when(bookDB.getAllBooks()).thenReturn(books);

        // WHEN
        List<Book> actual = bookService.getAllBooks();

        //THEN
        assertEquals(books, actual);
    }

    @Test
    void getBookById_ShouldReturn_BookForGivenId(){
        // GIVEN
        String id = "1";
        when(bookDB.getBookById(id)).thenReturn(new Book("testBook", "testauthor", "13"));

        // WHEN
        Book actual = bookService.getBookById(id);

        //THEN
        assertEquals(new Book("testBook", "testauthor", "13"), actual);
    }

}