package com.framework.springboot.yeoutside.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * DB 설정 class
 */
@Configuration
public class DatabaseConfig {

    /**
     * .yml 파일에서 읽어온 정보로 HikariConfig 객체 생성
     */
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariConfig hikariConfig() {

        return new HikariConfig();
    }

    /**
     * HikariConfig 객체로 DB와 연결하는 DataSource 생성
     */
    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {

        return new HikariDataSource(hikariConfig);
    }
}
