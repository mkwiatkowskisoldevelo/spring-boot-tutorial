package com.sda.springbootdemo.exercises.exception;

import java.util.List;
import lombok.Getter;
import org.springframework.validation.FieldError;

public class BindingResultException extends RuntimeException {

    @Getter
    private final List<FieldError> errors;

    public BindingResultException(List<FieldError> errors) {
        this.errors = errors;
    }
}
