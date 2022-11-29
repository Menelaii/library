package ru.pp.library.utils;

import java.util.List;

import org.springframework.validation.FieldError;

public class ErrorMessageBuilder {
    
    public static String buildErrorsMessage(List<FieldError> errors) {
        StringBuilder builder = new StringBuilder();
        for (FieldError error : errors) {
            builder.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }

        return builder.toString();
    }
}
