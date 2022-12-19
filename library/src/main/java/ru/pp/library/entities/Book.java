package ru.pp.library.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book extends AbstractEntity {

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 0, max = 120, message = "Название должно быть больше 0 и меньше 120 символов")
    @Column(name = "name", unique = true)
    private String name;

    @NotEmpty(message = "Название издательства не должно быть пустым")
    @Size(max = 120, message = "Название издательства должно быть меньше 120 символов")
    @Column(name = "publisher_name")
    private String publisherName;

    @NotNull(message = "Автор не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @NotNull(message = "Жанр не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "library_card_id", referencedColumnName = "id")
    private LibraryCard currentOwner;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Record> records;

    @Transient
    private boolean expired;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LibraryCard getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(LibraryCard currentOwner) {
        this.currentOwner = currentOwner;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
