package com.github.springtemplate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "공통응답 폼")
public abstract class ApiResponse<T> implements Serializable {
    @Schema(name = "요청 성공 여부")
    private final boolean success;
    @Schema(name = "응답 상태 코드")
    private final int status;
    @Schema(name = "응답 메세지")
    private final String message;
    @Schema(name = "응답 데이터")
    private final T data;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>(true, 200, message, data) {};
    }

    public static <T> ApiResponse<T> fail(int status, String message) {
        return new ApiResponse<T>(false, status, message, null) {};
    }

    public ApiResponse (boolean success, int status, String message, T data) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public ApiResponse () {
        this.success = false;
        this.status = 500;
        this.message = "이런 응답이 어떻게 해서 나오게되었는지 모르겠습니다. 전적으로 백엔드의 책임입니다.";
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
