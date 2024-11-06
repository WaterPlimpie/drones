package com.demo.drones.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Drone Management API", version = "v1"))
class OpenApiConfig {

    @Bean
    public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

    @Bean
    public GroupedOpenApi v2Group() {
        return GroupedOpenApi.builder()
                .group("v1")
                .displayName("Drone Management (v1)")
                .pathsToMatch("/api/v1/**")
                .build();
    }
}
