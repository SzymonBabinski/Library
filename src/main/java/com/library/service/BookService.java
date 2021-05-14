package com.library.service;

import com.library.dao.AuthorsAndAvgRatingOnly;
import com.library.domain.Book;
import com.library.domain.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> saveAll(List<Book> bookList);

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByCategory(String category);

    List<AuthorsAndAvgRatingOnly> findAuthorsByRatings();

    Optional<Book> findBookWithPagesGreaterThan(int pageCount);

    List<Book> findBooksByPagesPerHourAndHoursDaily(int pagesPerHour, int hoursDaily);

    List<Category> findAllCategories();

    List<Book> findAllBooks();
}
