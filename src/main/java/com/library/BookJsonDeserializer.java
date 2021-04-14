package com.library;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookJsonDeserializer extends JsonDeserializer<Book> {

    @Override
    public Book deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Book book = new Book();
        JsonNode node = p.getCodec().readTree(p);
        JsonNode volumeInfo = node.get("volumeInfo");

        // ISBN
        HashMap<String, String> typeIdMap = new HashMap<>();
        if(volumeInfo.has("industryIdentifiers")){
            JsonNode industryIdentifiers = volumeInfo.get("industryIdentifiers");
            if(industryIdentifiers.isArray()){
                for(final JsonNode jsonNode: industryIdentifiers){
                    String type = jsonNode.get("type").asText();
                    String value = jsonNode.get("identifier").asText();
                    typeIdMap.put(type,value);
                }
            }
        }

        if(typeIdMap.containsKey("ISBN_13")){
            book.setIsbn(typeIdMap.get("ISBN_13"));
        }else{
            String id = node.get("id").asText();
            book.setIsbn(id);
        }

        //TITLE
        if(volumeInfo.has("title")){
            String title = volumeInfo.get("title").asText();
            book.setTitle(title);
        }
        //SUBTITLE
        if (volumeInfo.has("subtitle")) {
            String subtitle = volumeInfo.get("subtitle").asText();
            book.setSubtitle(subtitle);
        }
        //PUBLISHER
        if(volumeInfo.has("publisher")){
            String publisher = volumeInfo.get("publisher").asText();
            book.setPublisher(publisher);
        }
        //PUBLISHED DATE
        if(volumeInfo.has("publishedDate")){
            long publishedDate = volumeInfo.get("publishedDate").asLong();
            book.setPublishedDate(publishedDate);
        }
        //DESCRIPTION
        if(volumeInfo.has("description")){
            String description = volumeInfo.get("description").asText();
            book.setDescription(description);
        }
        //PAGE COUNT
        if(volumeInfo.has("pageCount")){
            int pageCount = volumeInfo.get("pageCount").asInt();
            book.setPageCount(pageCount);
        }
        //THUMBNAIL
        if(volumeInfo.get("imageLinks").has("thumbnail")){
            String thumbnailUrl = volumeInfo.get("imageLinks").get("thumbnail").asText();
            book.setThumbnailUrl(thumbnailUrl);
        }
        //LANGUAGE
        if(volumeInfo.has("language")){
            String language = volumeInfo.get("language").asText();
            book.setLanguage(language);
        }
        //PREVIEW LINK
        if(volumeInfo.has("previewLink")){
            String previewLink = volumeInfo.get("previewLink").asText();
            book.setPreviewLink(previewLink);
        }
        //AVERAGE RATING
        if(volumeInfo.has("averageRating")){
            double averageRating = volumeInfo.get("averageRating").asDouble();
            book.setAverageRating(averageRating);
        }

        // AUTHORS
        if(volumeInfo.has("authors")){
            JsonNode authors = volumeInfo.get("authors");
            List<Author> authorsList = new ArrayList<>();
            if(authors.isArray()){
                for(final JsonNode jsonNode:authors){
                    authorsList.add(new Author(jsonNode.textValue()));
                }
            }
            book.setAuthors(authorsList);
        }

        //CATEGORIES
        if(volumeInfo.has("categories")){
            JsonNode categories = volumeInfo.get("categories");
            List<Category> categoryList = new ArrayList<>();
            if(categories.isArray()){
                for(final JsonNode jsonNode:categories){
                    categoryList.add(new Category(jsonNode.asText()));
                }
            }
            book.setCategories(categoryList);
        }

        return book;
    }
}
