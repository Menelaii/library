package ru.pp.library.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Library_card")
public class LibraryCard extends AbstractEntity {

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 3, max = 60, message = "ФИО должно быть больше 3 и меньше 60 символов")
    @Column(name = "full_name", unique = true)
    private String fullName;

    @NotEmpty(message = "Адрес не должен быть пустым")
    @Column(name = "address")
    private String address;

    @NotNull(message = "Дата рождения не должна быть пустой")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "currentOwner")
    private List<Book> books;

    @OneToMany(mappedBy = "libraryCard")
    private List<Record> records;

    public LibraryCard() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
