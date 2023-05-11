package com.example.enjoytrip.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("enjoytrip")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI().info(info());
    }

    private Info info() {
        return new Info()
                .title("EnjoyTrip API")
                .description("EnjoyTrip swagger config")
                .version("1.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }
}
