package com.github.springtemplate.exception.errorcode;

import com.github.springtemplate.exception.ApiException;
import org.springframework.http.HttpStatus;

public enum JwtErrorCode implements ErrorCode {
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "토큰의 형식을 만족하지 않습니다."),
    EMPTY_JWT(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다.")
    ;

    JwtErrorCode(HttpStatus status, String message) {
        this.apiException = new ApiException(status.value(), message) {};
    }

    private final ApiException apiException;

    @Override
    public ApiException getException() {
        return apiException;
    }
}
