package com.mariakomar.slackjsonreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * Main class.
 *
 * Created by Maria Komar on 24.01.17.
 */
@SpringBootApplication
public class JsonReaderMVC {
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public static void main(String[] args) {
        SpringApplication.run(JsonReaderMVC.class, args);
    }
}
