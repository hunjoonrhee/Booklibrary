package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private BookDB bookDB;

    @Autowired
    public BookService(BookDB bookDB) {
        this.bookDB = bookDB;
    }

    public List<Book> getAllBooks(){
        return bookDB.getAllBooks();
    }

    public Book getBookById(String id){
        Book foundBook = bookDB.getBookById(id);

        if(foundBook==null){
            throw new NoSuchElementException("No Book was found with id: " + id);
        }
        return bookDB.getBookById(id);
    }

    public Book postNewBook(Book book){
        Book newBook = bookDB.postNewBook(book);
        return newBook;
    }

}
