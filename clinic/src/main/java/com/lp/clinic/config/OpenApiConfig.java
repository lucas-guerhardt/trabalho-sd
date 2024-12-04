package com.lp.clinic.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Central", version = "v1"), tags = {
                @Tag(name = "Consultation Controller", description = "Endpoints relacionados as Consultas"),
})
public class OpenApiConfig {
}