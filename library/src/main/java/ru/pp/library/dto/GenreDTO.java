package ru.pp.library.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class GenreDTO {
    private Integer id;

    @Size(min = 1, max = 120, message = "Название жанра должно быть больше 1 и меньше 120 символов")
    @NotEmpty(message = "Название жанра не должно быть пустым")
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
