package com.advise_clothes.backend.exception;

import lombok.Getter;

/**
 * status : 400
 */
@Getter
public class InvalidRequest extends SuperException {
    private static final String MESSAGE = "잘못된 요청입니다.";

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE, fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
