package com.example.auth.javabooktdd;

import com.example.auth.javabooktdd.global.config.repository.TestRepositoryConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@Import(TestRepositoryConfig.class)
class JavaBookTddApplicationTests {

    @Test
    void contextLoads() {
    }

}
