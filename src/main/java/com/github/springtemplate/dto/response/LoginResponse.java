package com.github.springtemplate.dto.response;

import com.github.springtemplate.dto.request.LoginRequest;
import com.github.springtemplate.entity.Authorization;
import lombok.Builder;

@Builder
public record LoginResponse(
        String token,
        String email
) {
    public static LoginResponse from(Authorization savedData) {
        return LoginResponse.builder()
                .email(savedData.getEmail())
                .token(savedData.getToken())
                .build();
    }
}
