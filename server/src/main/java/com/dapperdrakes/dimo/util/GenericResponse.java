package com.dapperdrakes.dimo.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GenericResponse {

    private boolean success;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String error;
    private Object data;
    private Object message;
    public GenericResponse() {
        success = true;
    }

    public GenericResponse(Object data) {
        success = true;
        this.data = data;
    }

    public GenericResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }

    public GenericResponse(final String message) {
        this.message = message;
    }

    public GenericResponse(final String message, final String error) {
        this.message = message;
        this.error = error;
    }

    public GenericResponse(List<ObjectError> allErrors, String error) {
        this.error = error;
        String temp = allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            } else {
                return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            }
        }).collect(Collectors.joining(","));
        this.message = "[" + temp + "]";
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(final Object message) {
        this.message = message;
    }
}
