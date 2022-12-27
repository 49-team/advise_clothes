package com.advise_clothes.backend.exception;

/**
 * status : 404
 */
public class ClothesNotFound extends SuperException {

    private static final String MESSAGE = "존재하지 않는 옷입니다.";

    public ClothesNotFound() {
        super(MESSAGE);
    }

    public ClothesNotFound(String fieldName, String message) {
        super(MESSAGE, fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
