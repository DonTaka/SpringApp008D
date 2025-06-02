package com.example.SpringApp008D.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Microservicios REST API Fullstack I")
                        .version("1.0")
                        .description("Gestion de microservicios para asignatura Fullstack I 2025")
        );
    }
}
