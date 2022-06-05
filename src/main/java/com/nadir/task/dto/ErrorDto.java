package com.nadir.task.dto;

import java.io.Serializable;

public class ErrorDto implements Serializable {
    private int code;
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ErrorDto setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
