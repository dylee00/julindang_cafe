package com.POG.julindang.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info=@Info(title = "Julindang cafe Service",
                description = "줄인당 카페 관련 API",
                version ="v1"))

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi cafeOpenApi() {
        String[] paths = {"/cafe/**", "/topping/**"};
        return GroupedOpenApi.builder()
                .group("카페 API v1")  // 그룹 이름을 설정한다.
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정한다.
                .build();
    }


}
