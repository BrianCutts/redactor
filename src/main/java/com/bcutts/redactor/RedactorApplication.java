package com.bcutts.redactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.bcutts.redactor.config.ConfigProperties")
public class RedactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedactorApplication.class, args);
    }

}
