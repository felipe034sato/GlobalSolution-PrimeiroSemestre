package br.com.orbitalwatch.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
            .title("OrbitalWatch API").version("v2.0")
            .description("API REST - Monitoramento ambiental via satelite | Global Solution 2026"));
    }
}
