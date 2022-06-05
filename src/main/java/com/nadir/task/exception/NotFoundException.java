package com.nadir.task.exception;

public class NotFoundException extends RuntimeException {
    private int code;

    public NotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
