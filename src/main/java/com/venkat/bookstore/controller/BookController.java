package com.venkat.bookstore.controller;

import com.venkat.bookstore.model.book.Book;
import com.venkat.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Controller for books
@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path = "")
    public @ResponseBody List<Book> books() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping(path = "/{bookId}")
    public ResponseEntity<Book> bookById(@PathVariable long bookId) {
        Book book = bookRepository.findBookByBookIdEquals(bookId);
        return (book == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(book);
    }

    @GetMapping(path = "/random")
    public ResponseEntity<List<Book>> booksByRandom() {
        List<Book> books = new ArrayList<>();
        books = (List<Book>) bookRepository.findBookByCategoryIdNot(3);
        Collections.shuffle(books);
        return ResponseEntity.ok(books.subList(0,3));
    }
}
