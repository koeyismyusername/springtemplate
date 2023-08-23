package com.github.springtemplate.controller;

import com.github.springtemplate.exception.ApiException;
import com.github.springtemplate.exception.errorcode.TestErrorCode;
import com.github.springtemplate.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping("/")
    public ApiResponse<String> test() {
        throw TestErrorCode.TEST_ERROR_CODE.exception();

//        return ApiResponse.success("응답에 성공했습니다.", "hello, world");
    }
}
