package com.github.springtemplate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Springdoc 테스트",
                description = "Springdoc을 활용한 Swagger-ui 테스트",
                version = "v1.0.0"
        )
)
@SecuritySchemes({
        @SecurityScheme(
                type = SecuritySchemeType.HTTP,
                name = "JWT 토큰 인증",
                description = "로그인할 때 받았던 JWT 토큰을 기입해주세요.",
                in = SecuritySchemeIn.HEADER,
                paramName = HttpHeaders.AUTHORIZATION,
                scheme = "Bearer",
                bearerFormat = "JWT"
        )
})
public class SwaggerConfig {}
