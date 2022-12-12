package ru.pp.library.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookDTO {
    private Integer id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 0, max = 120, message = "Название должно быть больше 0 и меньше 120 символов")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
