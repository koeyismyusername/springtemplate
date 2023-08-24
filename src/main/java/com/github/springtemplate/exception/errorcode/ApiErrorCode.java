package com.github.springtemplate.exception.errorcode;

import com.github.springtemplate.exception.ApiException;
import org.springframework.http.HttpStatus;

public enum ApiErrorCode implements ErrorCode {
    FAIL_TO_SAVE(HttpStatus.INTERNAL_SERVER_ERROR, "서버 측의 문제로 데이터 저장에 실패했습니다.");

    ApiErrorCode(HttpStatus status, String message) {
        this.exception = new ApiException(status.value(), message) {};
    }
    private final ApiException exception;
    @Override
    public ApiException getException() {
        return exception;
    }
}
