package com.example.auth.javabooktdd.global.config.db;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile("!test")
@EnableJpaRepositories(
        basePackages = "com.example.auth.javabooktdd.infrastructure",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = ProductionRepository.class
        )
)
@EntityScan(basePackages = "com.example.auth.javabooktdd.infrastructure")
public class JpaConfig {
}
