package com.library.service;

import com.library.domain.Book;
import com.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class BookServiceImplTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    final Book BOOK_1 = new Book("1", "Test", "book1", "Tester", 2021L, "Test book1"
            , 20, "http://test1.pl", "PL", "http://prev.pl", 5.0, null, null);

    final Book BOOK_2 = new Book("2", "Test", "book2", "Tester", 2021L, "Test book2"
            , 20, "http://test2.pl", "PL", "http://prev.pl", 5.0, null, null);

    @Test
    void saveAll() {
        when(bookRepository.saveAll(Arrays.asList(BOOK_1, BOOK_2))).thenReturn(Arrays.asList(BOOK_1, BOOK_2));

        assertThat(bookService.saveAll(Arrays.asList(BOOK_1, BOOK_2))).containsOnly(BOOK_1, BOOK_2);
        assertEquals(2, bookService.saveAll(Arrays.asList(BOOK_1, BOOK_2)).size());
    }

    @Test
    void findBookByIsbn() {
        when(bookRepository.findById("1")).thenReturn(Optional.of(BOOK_1));

        assertEquals(Optional.of(BOOK_1), bookService.findBookByIsbn("1"));
    }

    @Test
    void findBooksByCategory() {
        when(bookRepository.findBooksByCategory("Computers")).thenReturn(Collections.singletonList(BOOK_1));

        assertNotEquals(Collections.singletonList(BOOK_2), bookService.findBooksByCategory("Computers"));
        assertEquals(Collections.singletonList(BOOK_1), bookService.findBooksByCategory("Computers"));
    }
}