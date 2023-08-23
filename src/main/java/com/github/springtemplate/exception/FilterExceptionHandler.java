package com.github.springtemplate.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtemplate.dto.response.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class FilterExceptionHandler extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        log.info("Api 요청! {} {}", request.getMethod(), request.getRequestURI());
        try {
            doFilter(request, response, filterChain);
            log.info("Api 응답! {}", HttpStatus.valueOf(response.getStatus()));
        }
        catch (Exception e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(500);
            response.getOutputStream().write(new ObjectMapper()
                    .writeValueAsString(ApiResponse.fail(500, e.getMessage())).getBytes(StandardCharsets.UTF_8));
            log.info("Api 응답! {}", HttpStatus.valueOf(response.getStatus()));
            e.printStackTrace();
        }
    }
}
