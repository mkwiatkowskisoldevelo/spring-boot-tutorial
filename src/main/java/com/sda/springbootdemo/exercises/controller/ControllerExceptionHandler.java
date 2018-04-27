package com.sda.springbootdemo.exercises.controller;

import com.sda.springbootdemo.exercises.exception.BindingResultException;
import com.sda.springbootdemo.exercises.exception.NotFoundException;
import com.sda.springbootdemo.exercises.exception.ValidationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handles exceptions for all {@link ValidationException}
     * thrown in {@link org.springframework.web.bind.annotation.RestController} methods.
     * For this exception server will respond with code 400.
     *
     * @param ex instance of exception
     * @return message from exception that will be used for response
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleValidationException(ValidationException ex) {
        return ex.getMessage();
    }

    /**
     * Handles exceptions for all {@link NotFoundException}
     * thrown in {@link org.springframework.web.bind.annotation.RestController} methods.
     * For this exception server will respond with code 404.
     *
     * @param ex instance of exception
     * @return message from exception that will be used for response
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Handles exceptions for all {@link BindingResultException}
     * thrown in {@link org.springframework.web.bind.annotation.RestController} methods.
     * For this exception server will respond with code 404.
     *
     * @param ex instance of exception
     * @return field errors from exception that will be used for response
     */
    @ExceptionHandler(BindingResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<FieldError> handleNotFoundException(BindingResultException ex) {
        return ex.getErrors();
    }
}
