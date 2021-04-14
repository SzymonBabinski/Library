package com.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.domain.Book;
import com.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;


@SpringBootApplication
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(BookService bookService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode items = mapper.readTree(new File("src/main/resources/json/books.json")).get("items");
            if(items.isArray()){
                for(final JsonNode item:items){
                    Book book = mapper.treeToValue(item,Book.class);
                }
            }
        };
    }
}
