package ru.pp.library.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Author")
public class Author extends AbstractEntity {
    
    @Size(min = 1, max = 60, message = "Псевдоним должен быть больше 1 и меньше 60 символов")
    @NotEmpty(message = "Псевдоним не должен быть пустым")
    @Column(name = "alias", unique = true)
    private String alias;

    @OneToMany(mappedBy = "author")
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
