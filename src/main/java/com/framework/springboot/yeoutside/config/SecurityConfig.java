package com.framework.springboot.yeoutside.config;

import com.framework.springboot.yeoutside.security.CustomAuthFailureHandler;
import com.framework.springboot.yeoutside.security.CustomAuthSuccessHandler;
import com.framework.springboot.yeoutside.security.CustomSessionExpiredStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

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
                        .loginProcessingUrl("/user/login")
                        .successHandler(customLoginSuccessHandler())
                        .failureHandler(customLoginFailureHandler()))
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/board/list")
                        .invalidateHttpSession(true))  // 로그아웃 시, 생성된 세션 삭제 처리
                .sessionManagement(session -> session
                        .sessionFixation().changeSessionId()  // 사용자 인증이 성공하며 세션 ID를 변경하여 기존 세션을 무효화하고 새로운 세션을 생성
                        .sessionAuthenticationStrategy(concurrentSession())
                        .maximumSessions(1)  // 동시에 허용되는 세션 수(0은 중복로그인 허용)
                        .maxSessionsPreventsLogin(false)  // 중복 session에 대한 처리, false는 이전 session 만료, true는 현재 session 만료
                        .expiredSessionStrategy(sessionInformationExpiredStrategy()));  // 세션이 만료되었을 때 어떻게 처리할지

        return httpSecurity.build();
    }

    /**
     * 로그인 성공 시 호출되는 Handler
     */
    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler() {

        return new CustomAuthSuccessHandler();
    }

    /**
     * 로그인 실패 시 호출되는 Handler
     */
    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler() {

        return new CustomAuthFailureHandler();
    }

    /**
     * 비밀번호 암호화
     */
    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /**
     * 인증 처리
     * - UserSecurityService와 passwordEncoder를 내부적으로 사용하여 인증과 권한 부여 프로세스를 처리
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 인증 session을 저장할 객체
     */
    @Bean
    public SessionRegistry sessionRegistry() {

        return new SessionRegistryImpl();
    }

    /**
     * session 만료 시 호출될 객체
     * @return
     */
    @Bean
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {

        return new CustomSessionExpiredStrategy();
    }

    /**
     * 인증 session을 관리할 방법 설정
     * <p>- SessionRegistry에 session을 저장하여 관리</p>
     */
    @Bean
    public CompositeSessionAuthenticationStrategy concurrentSession() {

        ConcurrentSessionControlAuthenticationStrategy concurrentAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        List<SessionAuthenticationStrategy> delegateStrategies = new ArrayList<>();
        delegateStrategies.add(concurrentAuthenticationStrategy);
        delegateStrategies.add(new SessionFixationProtectionStrategy());
        delegateStrategies.add(new RegisterSessionAuthenticationStrategy(sessionRegistry()));

        return new CompositeSessionAuthenticationStrategy(delegateStrategies);
    }
}
