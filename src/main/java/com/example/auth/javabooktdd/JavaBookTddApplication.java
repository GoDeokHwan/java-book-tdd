package com.example.auth.javabooktdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.auth.javabooktdd.infrastructure.*.repository")
public class JavaBookTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBookTddApplication.class, args);
    }

}
