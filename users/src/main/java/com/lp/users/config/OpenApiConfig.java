package com.lp.users.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "API de Usuários", version = "v1"),
    tags = {
        @Tag(name = "User Controller", description = "Endpoints relacionados aos usuários")
    }
)
public class OpenApiConfig {
}
