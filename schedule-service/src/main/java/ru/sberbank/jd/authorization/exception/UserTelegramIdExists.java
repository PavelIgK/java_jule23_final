package ru.sberbank.jd.authorization.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * UserNotFoundException exception.
 */
@Slf4j
public class UserTelegramIdExists extends RuntimeException {
    public UserTelegramIdExists(String message) {
        super(message);
        log.error(message);
    }
}
