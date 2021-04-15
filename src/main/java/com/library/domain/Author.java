package com.library.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    private String name;

    public Author(final String name) {
        this.name = name;
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
