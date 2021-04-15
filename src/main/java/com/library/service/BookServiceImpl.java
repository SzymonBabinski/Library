package com.library.service;

import com.library.domain.Book;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;

    BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> saveAll(final List<Book> bookList) {
        return bookRepository.saveAll(bookList);
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public List<Book> findBooksByCategory(String category) {
        return bookRepository.findBooksByCategory(category);
    }


}
