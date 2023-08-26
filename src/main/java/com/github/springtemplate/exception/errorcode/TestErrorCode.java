package com.github.springtemplate.exception.errorcode;

import com.github.springtemplate.exception.ApiException;
import org.springframework.http.HttpStatus;

public enum TestErrorCode implements ErrorCode {
    TEST_ERROR_CODE(HttpStatus.INTERNAL_SERVER_ERROR, "에러코드 테스트 메세지입니다.");

    TestErrorCode(HttpStatus status, String message) {
        this.message = message;
        this.status = status.value();
    }
    private final String message;
    private final int status;
    @Override
    public ApiException exception() {
        return new ApiException(status, message) {};
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
