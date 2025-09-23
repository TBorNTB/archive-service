package com.sejong.archiveservice.application.config;

import com.sejong.archiveservice.application.config.security.UserContextFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserContextFilter userContextFilter;

    private static final String[] SWAGGER_WHITELIST = {
            "/",
            "/webjars/swagger-ui/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui/index.html#/"
    };

    private static final String[] HEALTH_WHITELIST = {
            "/news/health",
            "/internal/**"
    };

    private static final String[] PUBLIC_GET_WHITELIST = {
            "/news/offset",
            "/news/cursor",
            "/news/{newsId}",
            "/cs-knowledge/offset",
            "/cs-knowledge/cursor",
            "/cs-knowledge/{csKnowledgeId}",
            "/cs-knowledge/{csKnowledgeId}/exists",
            "/cs-knowledge/category/**",
            "/cs-knowledge/unsent",
    };

    private static final String[] AUTH_POST = {
            "/news",
            "/news/files/presigned-url",
            "/cs-knowledge"
    };

    private static final String[] AUTH_PUT = {
            "/news/{newsId}",
            "/cs-knowledge/{csKnowledgeId}"
    };

    private static final String[] AUTH_DELETE = {
            "/news/{newsId}",
            "/cs-knowledge/{csKnowledgeId}"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // Swagger & Docs
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        // Health check
                        .requestMatchers(HEALTH_WHITELIST).permitAll()
                        // GET 조회는 인증 불필요
                        .requestMatchers(org.springframework.http.HttpMethod.GET, PUBLIC_GET_WHITELIST).permitAll()
                        // POST, PUT, DELETE 는 인증 필요
                        .requestMatchers(org.springframework.http.HttpMethod.POST, AUTH_POST).authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, AUTH_PUT).authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, AUTH_DELETE).authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(userContextFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}