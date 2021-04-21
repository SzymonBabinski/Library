package com.library.controller;

import com.library.domain.Book;
import com.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> bookDetails(@PathVariable String isbn) {
        return bookService
                .findBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/books/categories/{category}")
    public ResponseEntity<List<Book>> booksByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(bookService.findBooksByCategory(category));
    }

    @GetMapping("/authors/ratings")
    public ResponseEntity<List<Book.AuthorsNameAndAvgRatingOnly>> authorsByRatings() {
        return ResponseEntity.ok().body(bookService.findAuthorsByRatings());
    }

    @GetMapping("/books/volumes/{pageCount}")
    public ResponseEntity<Book> bookVolume(@PathVariable int pageCount) {
        return bookService
                .findBookWithPagesGreaterThan(pageCount)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/books/pages/{pagesPerHour}/hours/{hoursDaily}")
    public ResponseEntity<List<Book>> bestBooks(@PathVariable int pagesPerHour, @PathVariable int hoursDaily){
        return ResponseEntity.ok().body(bookService.findBooksByPagesPerHourAndHoursDaily(pagesPerHour,hoursDaily));
    }

}
