package com.nhom3.zoomanagement.utils;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Info info = new Info()
                .title("Zoo Management API")
                .version("1.0")
                .description("This API exposes endpoints to manage zoo.");
        return new OpenAPI().info(info).addSecurityItem(new SecurityRequirement().
                addList("Bearer Authentication"));
    }
}
