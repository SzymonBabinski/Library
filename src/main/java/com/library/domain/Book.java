package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
@Data
@AllArgsConstructor
public class Book {
    @Id
    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private long publishedDate;
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private double averageRating;
    private String[] authors;
    private String[] categories;
}
