package com.library.controller;

import com.library.domain.Book;
import com.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> bookDetails(@PathVariable String isbn){
        return bookService
                .findBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
