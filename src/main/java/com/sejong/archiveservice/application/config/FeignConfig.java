package com.sejong.archiveservice.application.config;

import com.sejong.archiveservice.infrastructure.user.UserServiceClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = UserServiceClient.class)
public class FeignConfig {
}
