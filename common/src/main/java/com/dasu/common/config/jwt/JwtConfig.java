package com.dasu.common.config.jwt;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    @Bean
    @ConditionalOnMissingBean
    public JwtProvider jwtProvider(JwtProperties properties) {
        return new JwtProvider(properties.getSecret(), properties.getExpirationMillis());
    }
}
