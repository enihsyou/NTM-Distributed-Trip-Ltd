package com.enihsyou.trip.bank.service.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate auth0RestTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder
            .rootUri("https://enihsyou.auth0.com/api/v2")
            .build();
    }
}
