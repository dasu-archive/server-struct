package com.dasu.common.config.security;


import com.dasu.common.config.jwt.JwtProvider;
import com.dasu.common.config.security.gateway.webFilter.JwtAuthenticationManager;
import com.dasu.common.config.security.gateway.webFilter.JwtServerSecurityContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class WebfluxSecurityConfig {



    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final JwtServerSecurityContextRepository jwtServerSecurityContextRepository;

    public WebfluxSecurityConfig(JwtProvider jwtProvider) {
        this.jwtAuthenticationManager = new JwtAuthenticationManager(jwtProvider);
        this.jwtServerSecurityContextRepository = new JwtServerSecurityContextRepository(this.jwtAuthenticationManager);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(jwtAuthenticationManager) // JWT 인증 매니저 설정
                .securityContextRepository(jwtServerSecurityContextRepository) // 컨텍스트 저장소 설정
                .authorizeExchange(auth -> auth
                        .pathMatchers("/api/user/auth/**").permitAll()  // 로그인/회원가입은 허용
                        .pathMatchers("/actuator/**").permitAll()
                        .anyExchange().authenticated()         // 나머지는 인증 필요
                )
                .build();
    }
}
