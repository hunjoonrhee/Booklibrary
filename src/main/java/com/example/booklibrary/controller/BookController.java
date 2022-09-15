package com.example.booklibrary.controller;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookDB;
import com.example.booklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(path="{id}")
    public Book getBookById(@PathVariable String id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book postNewBook(@RequestBody Book book){
        return bookService.postNewBook(book);
    }

}
