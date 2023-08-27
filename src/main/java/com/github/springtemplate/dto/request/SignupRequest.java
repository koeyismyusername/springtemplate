package com.github.springtemplate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(title = "회원가입 요청 객체")
public record SignupRequest(
        @Schema(title = "이메일", description = "사용자 이메일")
        String email,
        @Schema(title = "비밀번호")
        String password,
        @Schema(title = "이름")
        String name,
        @Schema(title = "나이")
        int age
){

}
