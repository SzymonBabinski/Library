package com.library.controller;

import com.library.service.BookService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    BookController(final BookService bookService) {
        this.bookService = bookService;
    }




}
