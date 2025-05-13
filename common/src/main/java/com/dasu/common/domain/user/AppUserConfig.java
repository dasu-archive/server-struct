package com.dasu.common.domain.user;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.dasu.common.domain.user")
@EntityScan("com.dasu.common.domain.user.entity")
@EnableJpaRepositories("com.dasu.common.domain.user.repository")
public class AppUserConfig {
}
