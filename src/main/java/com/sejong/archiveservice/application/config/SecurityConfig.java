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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //세션 설정 > session을 stateless로 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        // Swagger 및 API 문서
                        .requestMatchers("/", "/webjars/swagger-ui/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui/index.html#/").permitAll()
                        // Health check
                        .requestMatchers("/news/health").permitAll()
                        // 인증 불필요 - 조회 관련
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/news/offset", "/news/cursor", "/news/{newsId}").permitAll()
                        // 인증 필요 - 생성, 수정, 삭제
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/news", "/news/files/presigned-url").authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/news/{newsId}").authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/news/{newsId}").authenticated()
                        .anyRequest().authenticated())
                .addFilterBefore(userContextFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
