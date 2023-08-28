package com.github.springtemplate.exception;

import com.github.springtemplate.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ServletExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<?>> handleApiException(ApiException e) {
        ApiResponse<?> data = ApiResponse.fail(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(data);
    }
}
