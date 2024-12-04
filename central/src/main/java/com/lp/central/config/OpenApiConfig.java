package com.lp.central.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Central", version = "v1"), tags = {
        @Tag(name = "Guardian Controller", description = "Endpoints relacionados aos Guardiões"),
        @Tag(name = "Pet Controller", description = "Endpoints relacionados aos Pets")
})
public class OpenApiConfig {
}