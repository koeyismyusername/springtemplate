package com.github.springtemplate.dto.response;

import com.github.springtemplate.entity.User;
import lombok.Builder;

@Builder
public record UserResponse(
        int id,
        String email,
        String name,
        int age
) {
    public static UserResponse from (User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }
}
