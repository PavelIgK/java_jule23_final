package ru.sberbank.jd.authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sberbank.jd.authorization.exception.UserNotFoundException;
import ru.sberbank.jd.authorization.exception.UserPasswordIncorrect;
import ru.sberbank.jd.authorization.model.ErrorResponse;

/**
 * ErrorHandler.
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Некорректный логин.
     * Статус 404.
     *
     * @param e UserNotFoundException
     * @return ErrorResponse
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException e) {
        return new ErrorResponse(
                e.getMessage()
        );
    }

    /**
     * Некорректный пароль.
     * Статус 401.
     *
     * @param e UserPasswordIncorrect
     * @return ErrorResponse
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlePostNotFoundException(final UserPasswordIncorrect e) {
        return new ErrorResponse(
                e.getMessage()
        );
    }
}