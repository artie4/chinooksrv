package org.artie4.chinooksrv.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("API documentation")
                    .version("1.0")
                    .description("API for My Application")
                    .contact(
                        Contact()
                            .name("Support")
                            .email("support@example.com"),
                    ),
            ).components(
                Components()
                    .addSecuritySchemes(
                        "JWT", // Название схемы безопасности
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .`in`(SecurityScheme.In.HEADER)
                            .name("Authorization"),
                    ),
            )
    }
}
