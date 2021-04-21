package com.library.service;

import com.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> saveAll(List<Book> bookList);

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByCategory(String category);

    List<Book.AuthorsNameAndAvgRatingOnly> findAuthorsByRatings();
}
