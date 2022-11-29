package ru.pp.library.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book extends AbstractEntity {

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 0, max = 120, message = "ФИО должно быть больше 0 и меньше 120 символов")
    @Column(name = "name", unique = true)
    private String name;

    @Email
    @NotEmpty(message = "Название издательства не должно быть пустым")
    @Size(max = 120, message = "Название издательства должно быть меньше 120 символов")
    @Column(name = "publisher_name")
    private String publisherName;

    @NotEmpty(message = "Автор не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @NotEmpty(message = "Жанр не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @NotEmpty(message = "Изображение не должно быть пустым")
    @OneToOne(mappedBy = "book")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person currentOwner;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "taken_at")
    private Date takenAt;

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Person getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Person currentOwner) {
        this.currentOwner = currentOwner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
