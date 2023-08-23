package com.github.springtemplate.controller;

import com.github.springtemplate.dto.request.SignupRequest;
import com.github.springtemplate.dto.response.ApiResponse;
import com.github.springtemplate.dto.response.SignupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "회원가입, 로그인, 회원 정보 API")
public class UserController {

    @Operation(method = "GET", summary = "회원가입")
    @PostMapping("/auth/signup")
    public ApiResponse<SignupResponse> signup(@RequestBody @Parameter(name = "회원가입 요청 객체", required = true) SignupRequest request) {
        SignupResponse response = SignupResponse.builder().email(request.email()).id(1).name(request.name()).build();
        return ApiResponse.success("회원가입에 성공했습니다.", response);
    }
}
