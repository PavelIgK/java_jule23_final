package ru.sberbank.jd.authorization.model;

import lombok.Getter;

/**
 * ErrorResponse.
 */
@Getter
public class ErrorResponse {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

}