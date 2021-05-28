package com.library;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.domain.Book;
import com.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(BookService bookService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode items;
            List<Book> bookList = new ArrayList<>();
            String dataSource = System.getProperty("datasource");

            if (dataSource == null) {
                items = mapper.readTree(getClass().getResourceAsStream("/json/books.json")).get("items");
            } else {
                items = mapper.readTree(new File(dataSource)).get("items");
            }

            if (items.isArray()) {
                for (final JsonNode item : items) {
                    Book book = mapper.treeToValue(item, Book.class);
                    bookList.add(book);
                }
            }

            bookService.saveAll(bookList);
        };

    }
}
