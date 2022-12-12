package ru.pp.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidEntityException extends RuntimeException {
    public InvalidEntityException(String message) {
        super(message);
    } 
}
