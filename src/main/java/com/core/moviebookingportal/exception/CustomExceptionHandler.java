package com.core.moviebookingportal.exception;

import com.core.moviebookingportal.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class CustomExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ErrorResponseDto> handleApiException(ApiException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDto(ex.getMessage()),ex.getHttpStatus());
    }
}
