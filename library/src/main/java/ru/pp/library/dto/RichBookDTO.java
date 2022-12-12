package ru.pp.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class RichBookDTO extends BookDTO {
    @NotEmpty(message = "Название издательства не должно быть пустым")
    @Size(max = 120, message = "Название издательства должно быть меньше 120 символов")
    private String publisherName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate takenAt;

    @NotNull(message = "Автор не должен быть пустым")
    private AuthorDTO author;
    @NotNull(message = "Жанр не должен быть пустым")
    private GenreDTO genre;
    private PersonDTO currentOwner;

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public PersonDTO getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(PersonDTO currentOwner) {
        this.currentOwner = currentOwner;
    }

    public LocalDate getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDate takenAt) {
        this.takenAt = takenAt;
    }
}
