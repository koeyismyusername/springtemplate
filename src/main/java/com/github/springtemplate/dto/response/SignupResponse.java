package com.github.springtemplate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "회원가입 응답 객체")
public record SignupResponse (
        @Schema(name = "회원 id")
        int id,
        @Schema(name = "회원 이메일")
        String email,
        @Schema(name = "회원 이름")
        String name
){
}
