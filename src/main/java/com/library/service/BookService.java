package com.library.service;

import com.library.domain.Book;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveAll(List<Book> bookList){
        bookRepository.saveAll(bookList);
    }
}
