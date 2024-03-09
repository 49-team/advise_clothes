package com.advise_clothes.backend.exception;

import com.advise_clothes.backend.exception.SuperException.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
