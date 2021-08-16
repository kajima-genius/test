package com.music.example.backend.common.exception.handler;


import com.music.example.backend.common.exception.NotFoundException;
import com.music.example.backend.common.exception.wrapper.ExceptionWrapper;
import com.music.example.backend.user.exception.UserAlreadyExistException;
import com.music.example.backend.user.exception.UserEmailNotVerifiedException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionWrapper handleNotFoundException(NotFoundException exception) {
        return new ExceptionWrapper(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionWrapper handleUserAlreadyExistsException(UserAlreadyExistException exception) {
        return new ExceptionWrapper(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionWrapper handleNoHandlerFoundException(NoHandlerFoundException exception) {
        return new ExceptionWrapper(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ExceptionWrapper handleMethodNotAllowedException(HttpClientErrorException.MethodNotAllowed exception) {
        return new ExceptionWrapper(METHOD_NOT_ALLOWED, "An attempt to use a method that does not exist!");
    }

    @ExceptionHandler(UserEmailNotVerifiedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ExceptionWrapper handleUserEmailNotVerified(UserEmailNotVerifiedException exception) {
        return new ExceptionWrapper(UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionWrapper handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ExceptionWrapper(BAD_REQUEST, parseErrors(exception.getAllErrors()));
    }

    private List<String> parseErrors(List<ObjectError> errors) {
        return errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
