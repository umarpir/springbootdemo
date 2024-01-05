package com.sbdemo.springbootdemo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
public class RegistrationValidationException extends RuntimeException {
    public RegistrationValidationException(String message) {
        super(message);
    }
}
