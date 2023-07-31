package com.ecommerceservice.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidRequestException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public InvalidRequestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
