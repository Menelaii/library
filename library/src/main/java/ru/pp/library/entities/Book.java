package ru.pp.library.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person currentOwner;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "taken_at", columnDefinition = "DATE")
    private LocalDate takenAt;

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

    public Person getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Person currentOwner) {
        this.currentOwner = currentOwner;
    }

    public LocalDate getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDate takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
