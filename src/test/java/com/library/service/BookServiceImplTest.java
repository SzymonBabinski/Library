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

import java.util.*;

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
            , 200, "http://test1.pl", "PL", "http://prev.pl", 5.0, null, null);

    final Book BOOK_2 = new Book("2", "Test", "book2", "Tester", 2021L, "Test book2"
            , 200, "http://test2.pl", "PL", "http://prev.pl", 5.0, null, null);

    final List<Book> BOOK_LIST = Arrays.asList(BOOK_1, BOOK_2);


    @Test
    void saveAllTest() {
        when(bookRepository.saveAll(BOOK_LIST)).thenReturn(BOOK_LIST);

        assertThat(bookService.saveAll(BOOK_LIST)).containsOnly(BOOK_1, BOOK_2);
        assertEquals(2, bookService.saveAll(BOOK_LIST).size());
    }

    @Test
    void findBookByIsbnTest() {
        when(bookRepository.findById("1")).thenReturn(Optional.of(BOOK_1));

        assertEquals(Optional.of(BOOK_1), bookService.findBookByIsbn("1"));
    }

    @Test
    void findBooksByCategoryTest() {
        when(bookRepository.findBooksByCategory("Computers")).thenReturn(BOOK_LIST);

        assertEquals(BOOK_LIST, bookService.findBooksByCategory("Computers"));
    }

    @Test
    void findBookWithPagesGreaterThanTest() {
        when(bookRepository.findTopBookByPageCountGreaterThan(100)).thenReturn(Optional.of(BOOK_1));

        assertEquals(bookService.findBookWithPagesGreaterThan(100), Optional.of(BOOK_1));
    }

    @Test
    void findBooksByPagesPerHourAndHoursDailyTest() {
        final int PAGES_PER_HOUR = 20;
        final int VALID_HOURS_DAILY = 5;
        final int INVALID_HOURS_DAILY = 25;
        final int DAYS = 30;

        int validPagesPerMonth = PAGES_PER_HOUR * VALID_HOURS_DAILY * DAYS;


        when(bookRepository.findBooksByPagesMonthly(validPagesPerMonth)).thenReturn(BOOK_LIST);

        assertEquals(bookService.findBooksByPagesPerHourAndHoursDaily(PAGES_PER_HOUR, VALID_HOURS_DAILY),
                BOOK_LIST);
        assertEquals(bookService.findBooksByPagesPerHourAndHoursDaily(PAGES_PER_HOUR, INVALID_HOURS_DAILY),
                new ArrayList<Book>());
    }

}