package com.sejong.archiveservice.application.config;

import com.sejong.archiveservice.application.config.security.UserContextFilter;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserContextFilter userContextFilter) throws Exception {
        http
            .cors((corsCustomizer -> corsCustomizer.configurationSource(request -> {
              CorsConfiguration configuration = new CorsConfiguration();
              configuration.setAllowedOrigins(Arrays.asList(
                  "http://3.37.124.162:8000"
              ));
              configuration.setAllowedMethods(Collections.singletonList("*"));
              configuration.setAllowCredentials(true);
              configuration.setAllowedHeaders(Collections.singletonList("*"));
              configuration.setMaxAge(3600L);
              configuration.setExposedHeaders(Collections.singletonList("Authorization"));
              return configuration;
            })))
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(userContextFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                // Swagger UI 관련 경로 허용
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()

                .requestMatchers(HttpMethod.GET,"/archive/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/archive/**").permitAll()

                // 액추에이터 엔드포인트 허용
                .requestMatchers("/actuator/**").permitAll()
                // 나머지는 인증 필요
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
