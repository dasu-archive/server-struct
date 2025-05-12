package com.dasu.common.config.jwt;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    // Getter/Setter
    private String secret;
    private long expirationMillis;


}
