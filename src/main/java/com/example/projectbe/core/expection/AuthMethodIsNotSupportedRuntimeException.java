package com.example.projectbe.core.expection;

public class AuthMethodIsNotSupportedRuntimeException extends RuntimeException{
    public AuthMethodIsNotSupportedRuntimeException(String message) {
        super(message);
    }
}
