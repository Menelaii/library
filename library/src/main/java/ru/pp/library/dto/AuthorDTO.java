package ru.pp.library.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthorDTO {
    private Integer id;

    @Size(min = 1, max = 60, message = "Псевдоним должен быть больше 1 и меньше 60 символов")
    @NotEmpty(message = "Псевдоним не должен быть пустым")
    private String alias;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
