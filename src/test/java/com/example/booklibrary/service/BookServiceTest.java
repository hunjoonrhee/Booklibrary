package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookDB;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        when(bookDB.getBookById(id)).thenReturn(new Book("Hans im Glück", "Jacob Grimm", "1"));

        // WHEN
        Book actual = bookService.getBookById(id);

        //THEN
        assertEquals(new Book("Hans im Glück", "Jacob Grimm", "1"), actual);
    }

    @Test
    void getBookById_whenIdDoesNotExists_throwsException(){
        //GIVEN
        when(bookDB.getBookById("1")).thenReturn(null);

        //WHEN & THEN
        assertThrows(NoSuchElementException.class, () -> bookService.getBookById("1"));
    }

    @Test
    void postNewBook_ShouldReturn_NewAddedBook(){
        // GIVEN
        Book book = new Book("Hans im Glück", "Jacob Grimm", "1");

        when(bookDB.postNewBook(book)).thenReturn(book);

        // WHEN
        Book actual = bookService.postNewBook(book);

        //THEN
        assertEquals(book, actual);
    }

}