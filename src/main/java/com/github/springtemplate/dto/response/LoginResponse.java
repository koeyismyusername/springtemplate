package com.github.springtemplate.dto.response;

public record LoginResponse(
        String token,
        String email
) {
}
