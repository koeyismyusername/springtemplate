package com.github.springtemplate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "공통응답 폼")
public record ApiResponse<T>(
        @Schema(title = "요청 성공 여부")
        boolean success,
        @Schema(title = "응답 상태 코드")
        int status,
        @Schema(title = "응답 메세지")
        String message,
        @Schema(title = "응답 데이터")
        T data
) {
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, 200, message, data);
    }
    public static <T> ApiResponse<T> fail(int status, String message) {
        return new ApiResponse<>(false, status, message, null);
    }
}
