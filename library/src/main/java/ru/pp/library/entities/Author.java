package ru.pp.library.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Author")
public class Author extends AbstractEntity {
    
    @Size(min = 1, max = 60, message = "Псевдоним должен быть больше 1 и меньше 60 символов")
    @NotEmpty(message = "Псевдоним не должен быть пустым")
    @Column(name = "alias", unique = true)
    private String alias;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
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
