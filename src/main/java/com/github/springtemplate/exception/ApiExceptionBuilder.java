package com.github.springtemplate.exception;

import com.github.springtemplate.exception.errorcode.ErrorCode;

public class ApiExceptionBuilder {

    private ErrorCode errorCode;

    public ApiExceptionBuilder errorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }
    public ApiException build() {
        return new ApiException(errorCode) {};
    }
}
