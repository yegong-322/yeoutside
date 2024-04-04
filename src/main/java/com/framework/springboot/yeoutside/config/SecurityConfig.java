package com.framework.springboot.yeoutside.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security 설정 class
 */
@Configuration
@EnableWebSecurity  // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록함 -> Security 활성화
public class SecurityConfig {

    /**
     * 모든 URL 요청에 필터로 적용됨
     * <p>authorizeHttpRequests : 인증 페이지 설정</p>
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/board/list"));  // 성공 시, board/list 페이지로 이동함

        return httpSecurity.build();
    }
}
