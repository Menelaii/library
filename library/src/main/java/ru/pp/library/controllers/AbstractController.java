package ru.pp.library.controllers;

import org.springframework.validation.BindingResult;

import ru.pp.library.exceptions.InvalidEntityException;
import ru.pp.library.utils.ErrorMessageBuilder;

public abstract class AbstractController {
    
    protected void throwInvalidIfHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = ErrorMessageBuilder.buildErrorsMessage(bindingResult.getFieldErrors());
            throw new InvalidEntityException(message);
        }
    }
}
