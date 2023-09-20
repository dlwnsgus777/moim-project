package com.youth.moim.common.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindExceptions(BindException e) {
        e.printStackTrace();

        List<String> errors = e.getBindingResult().getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(
            new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors.toString())
        );
    }

    public record ErrorResponse(
        int statusCode,
        String message,
        LocalDateTime time
    ) {
        public ErrorResponse(int statusCode, String message) {
            this(statusCode, message, LocalDateTime.now());
        }
    }

}
