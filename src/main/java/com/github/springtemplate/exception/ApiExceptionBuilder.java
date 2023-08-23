package com.github.springtemplate.exception;

public class ApiExceptionBuilder {
    private int status;
    private String message;

    public ApiExceptionBuilder status(int status) {
        this.status = status;
        return this;
    }
    public ApiExceptionBuilder message(String message) {
        this.message = message;
        return this;
    }
    public ApiException build() {
        return new ApiException(status, message) {};
    }
}
