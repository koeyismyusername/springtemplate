package com.github.springtemplate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "회원가입 요청 객체")
public record SignupRequest(
        @Schema(name = "email")
        String email,
        @Schema(name = "비밀번호")
        String password,
        @Schema(name = "이름")
        String name
){

}
