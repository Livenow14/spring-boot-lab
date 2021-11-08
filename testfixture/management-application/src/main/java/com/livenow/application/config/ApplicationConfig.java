package com.livenow.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.livenow.application.domain.*")
@EntityScan("com.livenow.core.domain.*")
@EnableJpaRepositories("com.livenow.application.domain.*")
public class ApplicationConfig {
}
