package com.github.springtemplate.exception;

public abstract class ApiException extends RuntimeException{
    private final int status;

    public ApiException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() { return status; }
    public static ApiExceptionBuilder builder() { return new ApiExceptionBuilder(); }
}
