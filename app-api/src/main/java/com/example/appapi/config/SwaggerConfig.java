package com.example.appapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("A 프로젝트 기능 테스트")
                .description("A 프로젝트 기능을 테스트 하기 위한 웹 페이지")
                .version("1.0.0");
    }
}
