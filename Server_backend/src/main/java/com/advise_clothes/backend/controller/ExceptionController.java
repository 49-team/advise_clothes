package com.advise_clothes.backend.controller;

import com.advise_clothes.backend.exception.SuperException;
import com.advise_clothes.backend.exception.SuperException.Validation;
import com.advise_clothes.backend.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    /**
     * Domain에 붙은 @NotBlank, @NotNull 등의 validation을 처리하는 핸들러
     * @param e MethodArgumentNotValidException
     * @return ErrorResponse
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        Validation validation = Validation.builder()
                .fieldName(e.getFieldError().getField())
                .message(e.getFieldError().getDefaultMessage())
                .build();

        return ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(validation)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse bindException(MethodArgumentTypeMismatchException e) {
        Validation validation = Validation.builder()
                .fieldName(e.getName())
                .message(e.getMessage())
                .build();

        return ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(validation)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse bindException(BindException e) {
        return ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
    }

    @ExceptionHandler(SuperException.class)
    public ResponseEntity<ErrorResponse> superException(SuperException error) {
        int statusCode = error.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(error.getMessage())
                .validation(error.getValidation())
                .build();

        return ResponseEntity.status(statusCode)
                .body(body);
    }
}
