package com.library.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public String toString() {
        return name;
    }
}
