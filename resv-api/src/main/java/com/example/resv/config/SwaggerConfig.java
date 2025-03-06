package com.example.resv.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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
                .title("Eatzzy 식당 예약 API 명세서")
                .description("식당 예약 관련 API 명세서 페이지입니다.<br><br>" +
                        "<a href='http://localhost:8080/app/swagger-ui/index.html#/'>Eatzzy API 명세서 페이지 바로가기</a>")
                .version("1.0.0");
    }
}