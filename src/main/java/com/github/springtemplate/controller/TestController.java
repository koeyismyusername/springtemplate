package com.github.springtemplate.controller;

import com.github.springtemplate.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping("/")
    public ApiResponse<String> test() {
        return ApiResponse.success("응답에 성공했습니다.", "hello, world");
    }
}
