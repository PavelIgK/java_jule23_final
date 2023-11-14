package ru.sberbank.jd.authorization.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * UserPasswordIncorrect exception.
 */
@Slf4j
public class UserPasswordIncorrect extends RuntimeException {
    public UserPasswordIncorrect(String message) {
        super(message);
        log.error(message);
    }
}
