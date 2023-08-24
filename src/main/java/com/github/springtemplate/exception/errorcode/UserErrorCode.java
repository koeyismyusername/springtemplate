package com.github.springtemplate.exception.errorcode;

import com.github.springtemplate.exception.ApiException;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {
    EXISTS_EMAIL(HttpStatus.CONFLICT, "이미 회원가입된 이메일입니다."), 
    INVALID_EMAIL(HttpStatus.UNAUTHORIZED, "가입되지 않은 이메일입니다."),
    EXISTS_LOGIN(HttpStatus.CONFLICT, "이미 로그인되어 있습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 옳지 않습니다.");

    UserErrorCode(HttpStatus status, String message) {
        this.exception = new ApiException(status.value(), message) {};
    }
    private final ApiException exception;

    @Override
    public ApiException exception() {
        return exception;
    }
}
