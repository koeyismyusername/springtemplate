package com.github.springtemplate.dto.request;

import lombok.Builder;

@Builder
public record LoginRequest(
        String email,
        String password
) {
}
