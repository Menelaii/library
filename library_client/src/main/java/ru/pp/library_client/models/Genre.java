package ru.pp.library_client.models;

import java.util.List;

public class Genre extends AbstractEntity {

    private String name;
    private List<Book> books;

    public Genre() {
    }

    public Genre(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
