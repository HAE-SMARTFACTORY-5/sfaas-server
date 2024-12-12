package com.hae5.sfaas.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    // 1개만 있는 경우
    @Bean
    public OpenAPI api() {
        Info info = new Info()
                .version("v1.0.0")
                .title("HAE Team 5")
                .description("HAE Team 5's SFaaS Project Swagger");

        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/"));
    }

    @Bean
    public GroupedOpenApi allGroup() {
        return GroupedOpenApi.builder()
                .group("All")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi basicGroup() {
        return GroupedOpenApi.builder()
                .group("Basic")
                .pathsToMatch("/api/v1/basic/**")
                .build();
    }

}
