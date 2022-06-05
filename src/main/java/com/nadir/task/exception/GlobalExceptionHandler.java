package com.nadir.task.exception;

import com.nadir.task.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(exception.getCode()).body(new ErrorDto(exception.getCode(), exception.getMessage()));
    }

}
