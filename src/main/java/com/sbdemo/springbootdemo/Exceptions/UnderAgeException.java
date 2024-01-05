package com.sbdemo.springbootdemo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnderAgeException extends RuntimeException {
    public UnderAgeException(String message) {
        super(message);
    }
}
