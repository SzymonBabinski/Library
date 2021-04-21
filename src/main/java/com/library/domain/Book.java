package com.library.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.library.BookJsonDeserializer;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonDeserialize(using = BookJsonDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Book {
    @Id
    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private long publishedDate;
    @Column(length = 10000)
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private double averageRating;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Author> authors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Category> categories;


    public Book(final String isbn, final String title, final String subtitle, final String publisher,
                final long publishedDate, final String description, final int pageCount, final String thumbnailUrl,
                final String language, final String previewLink, final double averageRating,
                final List<Author> authors, final List<Category> categories) {
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.thumbnailUrl = thumbnailUrl;
        this.language = language;
        this.previewLink = previewLink;
        this.averageRating = averageRating;
        this.authors = authors;
        this.categories = categories;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(final long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(final int pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(final String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(final String previewLink) {
        this.previewLink = previewLink;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(final double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors = authors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    public interface AuthorsNameAndAvgRatingOnly {
        Double getAverageRating();

        String getName();
    }


}
