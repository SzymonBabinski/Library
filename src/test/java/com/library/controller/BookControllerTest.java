package com.library.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BookControllerTest {

    @Test
    public void book_details_returns_200_when_expected_isbn() {

        when()
                .get("/books/{isbn}", "9780471721260").
                then()
                .statusCode(200)
                .body("isbn", equalTo("9780471721260"));
    }

    @Test
    public void book_details_returns_404_when_wrong_isbn() {

        when()
                .get("/books/{isbn}", "wrongIsbn").
                then()
                .statusCode(404);
    }

    @Test
    public void books_categories_returns_200_and_correct_json_when_expected_category() {

        when()
                .get("/books/categories/{category}", "Computers").
                then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)),
                        "categories", everyItem(hasItem("Computers")));
    }

    @Test
    public void books_categories_returns_200_and_empty_json_when_wrong_category() {

        when()
                .get("/books/categories/{category}", "wrongCategory").
                then()
                .statusCode(200)
                .body("", hasSize(0));
    }

    @Test
    public void authors_rating_returns_200_and_correct_json() {
        when()
                .get("/authors/ratings").
                then()
                .statusCode(200)
                .body("name", notNullValue(),
                        "averageRating", notNullValue());
    }

    @Test
    public void books_volume_returns_200_and_correct_pageCount() {
        when()
                .get("/books/volumes/{pageCount}", 100).
                then()
                .statusCode(200)
                .body("pageCount", greaterThan(100));
    }

    @Test
    public void books_volume_returns_404_while_wrong_pageCount() {
        when()
                .get("/books/volumes/{pageCount}", 999999)
                .then()
                .statusCode(404);
    }

    @Test
    public void best_books_returns_correct_json_while_correct_pagesPerHour_and_hoursDaily() {
        int pagesPerMonth = 5 * 10 * 30; // 5 pages per hour * 10 hours daily * 30 days
        when()
                .get("/books/pages/{pagesPerHour}/hours/{hoursDaily}", 5, 10)
                .then()
                .statusCode(200)
                .body("pageCount", everyItem(lessThan(pagesPerMonth)),
                        "pageCount", everyItem(not(0)));
    }

    @Test
    void best_books_returns_empty_json_while_wrong_hoursDaily() {
        when()
                .get("/books/pages/{pagesPerHour}/hours/{hoursDaily}", 5, 25)
                .then()
                .statusCode(200)
                .body("", hasSize(0));
    }
}