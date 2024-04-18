package com.intervals.exception.service.types;

public class IllegalTypeException extends RuntimeException{
    public IllegalTypeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
