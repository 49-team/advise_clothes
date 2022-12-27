package com.advise_clothes.backend.response;

import com.advise_clothes.backend.exception.SuperException;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ErrorResponse {

    private final String message;
    private final String code;
    private final SuperException.Validation validation;

    @Builder
    public ErrorResponse(String message, String code, SuperException.Validation validation) {
        this.message = message;
        this.code = code;
        this.validation = validation;
    }
}
