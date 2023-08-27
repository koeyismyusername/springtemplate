package com.github.springtemplate.controller;

import com.github.springtemplate.dto.request.LoginRequest;
import com.github.springtemplate.dto.request.SignupRequest;
import com.github.springtemplate.dto.response.ApiResponse;
import com.github.springtemplate.dto.response.LoginResponse;
import com.github.springtemplate.dto.response.UserResponse;
import com.github.springtemplate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "회원가입, 로그인, 회원 정보 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/auth/signup")
    public ApiResponse<UserResponse> signup(@RequestBody @Parameter(name = "회원가입 요청 객체", required = true) SignupRequest request) {
        return userService.signup(request);
    }

    @Operation(summary = "로그인")
    @PostMapping("/auth/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Parameter(name = "로그인 요청 객체", required = true) LoginRequest request) {
        return userService.login(request);
    }

    @Operation(summary = "나이 증가시키기")
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> incrementAge(@PathVariable String id) {
        int idInt = Integer.parseInt(id);
        return userService.incrementAge(idInt);
    }
}
