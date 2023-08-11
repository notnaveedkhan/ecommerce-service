package com.ecommerceservice.application.controller;

import com.ecommerceservice.application.common.CommonText;
import com.ecommerceservice.application.exception.InvalidRequestException;
import com.ecommerceservice.application.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class CustomExceptionController {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(InvalidRequestException ex,
                                                                           HttpServletRequest request) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ExceptionResponse(CommonText.FAILURE, ex.getMessage(), request.getRequestURI(),
                        LocalDateTime.now().toString()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                   HttpServletRequest request) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ExceptionResponse(CommonText.FAILURE, ex.getAllErrors().get(0).getDefaultMessage(), request.getRequestURI(),
                        LocalDateTime.now().toString()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex,
                                                                                   HttpServletRequest request) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ExceptionResponse(CommonText.FAILURE, ex.getMessage(), request.getRequestURI(),
                        LocalDateTime.now().toString()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                                   HttpServletRequest request) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ExceptionResponse(CommonText.FAILURE, ex.getMessage(), request.getRequestURI(),
                        LocalDateTime.now().toString()));
    }

}
