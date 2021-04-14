package com.library;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.library.domain.Book;

import java.io.IOException;

public class BookJsonDeserializer extends JsonDeserializer<Book> {

    @Override
    public Book deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Book book = new Book();
        JsonNode node = p.getCodec().readTree(p);
        JsonNode volumeInfo = node.get("volumeInfo");

        // TODO: isbn

        if(volumeInfo.has("title")){
            String title = volumeInfo.get("title").asText();
            book.setTitle(title);
        }

        if (volumeInfo.has("subtitle")) {
            String subtitle = volumeInfo.get("subtitle").asText();
            book.setSubtitle(subtitle);
        }

        if(volumeInfo.has("publisher")){
            String publisher = volumeInfo.get("publisher").asText();
            book.setPublisher(publisher);
        }

        if(volumeInfo.has("publishedDate")){
            long publishedDate = volumeInfo.get("publishedDate").asLong();
            book.setPublishedDate(publishedDate);
        }

        if(volumeInfo.has("description")){
            String description = volumeInfo.get("description").asText();
            book.setDescription(description);
        }

        if(volumeInfo.get("imageLinks").has("thumbnail")){
            String thumbnailUrl = volumeInfo.get("imageLinks").get("thumbnail").asText();
            book.setThumbnailUrl(thumbnailUrl);
        }

        if(volumeInfo.has("language")){
            String language = volumeInfo.get("language").asText();
            book.setLanguage(language);
        }

        if(volumeInfo.has("previewLink")){
            String previewLink = volumeInfo.get("previewLink").asText();
            book.setPreviewLink(previewLink);
        }

        if(volumeInfo.has("averageRating")){
            double averageRating = volumeInfo.get("averageRating").asDouble();
            book.setAverageRating(averageRating);
        }

        // TODO: authors and categories

        return book;
    }
}
