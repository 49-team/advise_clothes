package com.advise_clothes.backend.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public abstract class SuperException extends RuntimeException {

    public Validation validation;

    public SuperException(String message) {
        super(message);
    }

    protected SuperException(String message, String fieldName, String validationMessage) {
        super(message);
        this.validation = Validation.builder()
                .fieldName(fieldName)
                .message(validationMessage)
                .build();
    }

    public abstract int getStatusCode();

    @RequiredArgsConstructor
    @Getter
    public static class Validation {
        private String fieldName;
        private String message;

        @Builder
        public Validation(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }

        public void addValidation(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }
    }
}
