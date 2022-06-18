package com.example.projectbe.core.expection;

public class UserNotFoundExceptionRuntimeException extends RuntimeException{
    public UserNotFoundExceptionRuntimeException(String message) {
        super(message);
    }
}
