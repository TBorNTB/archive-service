package com.sejong.archiveservice.application.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/archive-service"),
                @Server(url = "/")
        },
        info = @Info(
                title = "Archive API",
                version = "v1",
                description = "아카이브 API 문서입니다."
        )
)
@Configuration
public class SwaggerConfig {
}