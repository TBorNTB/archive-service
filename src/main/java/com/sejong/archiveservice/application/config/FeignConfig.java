package com.sejong.archiveservice.application.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.sejong.archiveservice.client")
public class FeignConfig {
}
