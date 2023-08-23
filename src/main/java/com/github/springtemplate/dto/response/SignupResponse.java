package com.github.springtemplate.dto.response;

import lombok.Builder;

@Builder
public record SignupResponse (
        int id,
        String email,
        String name
){
}
