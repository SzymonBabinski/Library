package com.library.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.Category;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class BookJsonDeserializer extends JsonDeserializer<Book> {

    @Override
    public Book deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        Book book = new Book();
        JsonNode node = p.getCodec().readTree(p);
        JsonNode volumeInfo = node.get("volumeInfo");

        // ISBN
        HashMap<String, String> typeIdMap = new HashMap<>();
        if (volumeInfo.has("industryIdentifiers")) {
            JsonNode industryIdentifiers = volumeInfo.get("industryIdentifiers");
            if (industryIdentifiers.isArray()) {
                for (final JsonNode jsonNode : industryIdentifiers) {
                    String type = jsonNode.get("type").asText();
                    String value = jsonNode.get("identifier").asText();
                    typeIdMap.put(type, value);
                }
            }
        }

        if (typeIdMap.containsKey("ISBN_13")) {
            book.setIsbn(typeIdMap.get("ISBN_13"));
        } else {
            String id = node.get("id").asText();
            book.setIsbn(id);
        }


        //TITLE
        deserializeAsSimpleField(volumeInfo, "title", JsonNode::asText).ifPresent(book::setTitle);

        //SUBTITLE
        deserializeAsSimpleField(volumeInfo, "subtitle", JsonNode::asText).ifPresent(book::setSubtitle);

        //PUBLISHER
        deserializeAsSimpleField(volumeInfo, "publisher", JsonNode::asText).ifPresent(book::setPublisher);

        //PUBLISHED DATE
        if (volumeInfo.has("publishedDate")) {
            String publishedDate = volumeInfo.get("publishedDate").textValue();
            List<SimpleDateFormat> knownPatterns = Arrays.asList(
                    new SimpleDateFormat("yyyy"),
                    new SimpleDateFormat("yyyy-MM-dd")
            );

            for (SimpleDateFormat pattern : knownPatterns) {
                try {
                    Date date = pattern.parse(publishedDate);
                    long unixTime = date.getTime();
                    book.setPublishedDate(unixTime);
                } catch (ParseException ignored) {
                }
            }
        }

        //DESCRIPTION
        deserializeAsSimpleField(volumeInfo, "description", JsonNode::asText).ifPresent(book::setDescription);

        //PAGE COUNT
        deserializeAsSimpleField(volumeInfo, "pageCount", JsonNode::asInt).ifPresent(book::setPageCount);

        //THUMBNAIL
        deserializeAsSimpleField(volumeInfo.get("imageLinks"), "thumbnail", JsonNode::asText).ifPresent(book::setThumbnailUrl);

        //LANGUAGE
        deserializeAsSimpleField(volumeInfo, "language", JsonNode::asText).ifPresent(book::setLanguage);

        //PREVIEW LINK
        deserializeAsSimpleField(volumeInfo, "previewLink", JsonNode::asText).ifPresent(book::setPreviewLink);

        //AVERAGE RATING
        deserializeAsSimpleField(volumeInfo, "averageRating", JsonNode::asDouble).ifPresent(book::setAverageRating);

        // AUTHORS
        if (volumeInfo.has("authors")) {
            JsonNode authors = volumeInfo.get("authors");
            List<Author> authorsList = new ArrayList<>();
            if (authors.isArray()) {
                for (final JsonNode jsonNode : authors) {
                    authorsList.add(new Author(jsonNode.textValue()));
                }
            }
            book.setAuthors(authorsList);
        }

        //CATEGORIES
        if (volumeInfo.has("categories")) {
            JsonNode categories = volumeInfo.get("categories");
            List<Category> categoryList = new ArrayList<>();
            if (categories.isArray()) {
                for (final JsonNode jsonNode : categories) {
                    categoryList.add(new Category(jsonNode.textValue()));
                }
            }
            book.setCategories(categoryList);
        }

        return book;
    }


    private <T> Optional<T> deserializeAsSimpleField(JsonNode node, String property, Function<JsonNode, T> mapper) {
        if (node.has(property)) {
            return Optional.of(mapper.apply(node.get(property)));
        } else {
            return Optional.empty();
        }
    }

}
