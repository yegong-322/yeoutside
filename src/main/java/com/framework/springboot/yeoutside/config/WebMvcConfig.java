package com.framework.springboot.yeoutside.config;

import com.framework.springboot.yeoutside.xss.XSSFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MVC 설정 config class
 */
@RequiredArgsConstructor  // 생성자 주입을 임의의 코드 없이 자동으로 설정해줌
@Configuration
public class WebMvcConfig {

    /**
     * Xss 필터 적용
     */
    @Bean
    public FilterRegistrationBean filterBean() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new XSSFilter());
        registrationBean.addUrlPatterns("/*");  //전체 URL 포함

        return registrationBean;
    }
}
