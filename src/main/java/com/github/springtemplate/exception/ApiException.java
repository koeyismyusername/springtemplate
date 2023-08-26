package com.github.springtemplate.exception;

import com.github.springtemplate.exception.errorcode.ErrorCode;

public abstract class ApiException extends RuntimeException{
    private final int status;

    public ApiException(int status, String message) {
        super(message);
        this.status = status;
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }

    public int getStatus() { return status; }
    public static ApiExceptionBuilder builder() { return new ApiExceptionBuilder(); }
}
