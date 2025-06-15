package com.hyodori.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("효도리 API 문서")
                        .description("효도리 백엔드 API 문서입니다.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("효도리 개발팀")
                                .email("hyodori@example.com")
                                .url("https://github.com/hyodori"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("로컬 개발 서버"),
                        new Server()
                                .url("https://api.hyodori.com")
                                .description("프로덕션 서버")
                ));
    }
} 