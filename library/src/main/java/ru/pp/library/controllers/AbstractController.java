package ru.pp.library.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ru.pp.library.errorResponses.ErrorResponse;
import ru.pp.library.exceptions.InvalidEntityException;
import ru.pp.library.exceptions.NotFoundException;
import ru.pp.library.utils.ErrorMessageBuilder;

import java.sql.SQLException;

public abstract class AbstractController {
    
    protected void throwInvalidIfHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = ErrorMessageBuilder.buildErrorsMessage(bindingResult.getFieldErrors());
            throw new InvalidEntityException(message);
        }
    }
    
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
            "Объекта с таким id не существует", 
            System.currentTimeMillis()
        );

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(InvalidEntityException e) {
        ErrorResponse errorResponse = new ErrorResponse(
            e.getMessage(),
            System.currentTimeMillis()
        );

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SQLException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
    }
}
