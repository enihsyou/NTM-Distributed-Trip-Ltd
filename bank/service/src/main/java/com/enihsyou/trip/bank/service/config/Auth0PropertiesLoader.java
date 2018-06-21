package com.enihsyou.trip.bank.service.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:auth0.properties")
public class Auth0PropertiesLoader {

    @Value("${auth0.apiAudience}")
    private String apiAudience;

    @Value("${auth0.issuer}")
    private String issuer;

    @Value("${auth0.client_id}")
    private String clientId;

    @Value("${auth0.client_secret}")
    private String clientSecret;

    @Value("${auth0.access_token}")
    private String accessToken;

    @Bean
    public TokenDTO tokenDTO() {
        return new TokenDTO(clientId, clientSecret, apiAudience);
    }

    @Bean
    public String accessToken() {return accessToken;}

    @Bean
    public JwtWebSecurityConfigurer jwtWebSecurityConfigurer() {
        return JwtWebSecurityConfigurer
            .forRS256(apiAudience, issuer);
    }

    @lombok.Value
    public static class TokenDTO {

        private String grant_type;

        private String client_id;

        private String client_secret;

        private String audience;

        private TokenDTO(String clientId, String clientSecret, String apiAudience) {
            this.grant_type = "client_credentials";
            this.client_id = clientId;
            this.client_secret = clientSecret;
            this.audience = apiAudience;
        }
    }
}
