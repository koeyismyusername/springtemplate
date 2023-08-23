package com.github.springtemplate.exception;

import com.github.springtemplate.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ServletExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ApiResponse<?> handleApiException(ApiException e, HttpServletResponse response) {
        response.setStatus(e.getStatus());
        return ApiResponse.fail(e.getStatus(), e.getMessage());
    }
}
