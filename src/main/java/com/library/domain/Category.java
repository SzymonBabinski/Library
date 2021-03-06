package com.library.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    private String name;

    public Category(final String name) {
        this.name = name;
    }

    public Category() {
    }

    private String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = name;
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
