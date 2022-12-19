package ru.pp.library_client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RichBookDTO extends BookDTO {

    private String publisherName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate takenAt;
    private AuthorDTO author;
    private GenreDTO genre;
    private LibraryCardDTO currentOwner;

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

    public LibraryCardDTO getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(LibraryCardDTO currentOwner) {
        this.currentOwner = currentOwner;
    }

    public LocalDate getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDate takenAt) {
        this.takenAt = takenAt;
    }
}
