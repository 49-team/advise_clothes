package com.advise_clothes.backend.exception;

/**
 * status : 404
 */
public class CompanyNotFound extends SuperException {
    private static final String MESSAGE = "회사를 찾을 수 없습니다.";

    public CompanyNotFound() {
        super(MESSAGE);
    }
    public CompanyNotFound(String fieldName, String message) {
        super(MESSAGE, fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
