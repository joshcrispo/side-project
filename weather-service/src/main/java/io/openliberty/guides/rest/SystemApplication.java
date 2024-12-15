package io.openliberty.guides.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args); // Bootstraps the Spring Boot application
    }

    // Define RestTemplate as a Bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
