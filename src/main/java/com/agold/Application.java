package com.agold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Import;

/**
 * Bootstraps the Spring Boot com.agold.Application
 *
 * Created by fredjean on 9/21/15.
 */


@SpringBootApplication
@Import(JpaConfig.class)
@EntityScan("com.agold")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
