package com.github.springtemplate.util;

public abstract class ApiResponse<T>{
    private final boolean success;
    private final int status;
    private final String message;
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
