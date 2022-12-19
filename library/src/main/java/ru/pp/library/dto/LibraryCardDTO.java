package ru.pp.library.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LibraryCardDTO {

    private Integer id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 3, max = 60, message = "ФИО должно быть больше 3 и меньше 60 символов")
    private String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
