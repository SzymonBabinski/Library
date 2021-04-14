package com.library.service;

import com.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void saveAll(List<Book> bookList);

    List<Book> findAll();

    Book save(Book book);

    Optional<Book> findBookByIsbn(String isbn);
}
