package ru.pp.library_client.models;

import java.util.List;

public class Author extends AbstractEntity {

    private String alias;
    private List<Book> books;

    public Author() {
    }

    public Author(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
