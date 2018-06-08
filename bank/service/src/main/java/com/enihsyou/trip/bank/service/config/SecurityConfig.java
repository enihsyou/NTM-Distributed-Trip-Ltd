package com.enihsyou.trip.bank.service.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:auth0.properties")
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth0.apiAudience}")
    private String apiAudience;

    @Value("${auth0.issuer}")
    private String issuer;

    @Value("${auth0.client_id}")
    private String clientId;

    @Value("${auth0.client_secret}")
    private String clientSecret;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
            .forRS256(apiAudience, issuer)
            .configure(http)
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers(HttpMethod.GET, "/api/public").permitAll()
            .antMatchers(HttpMethod.GET, "/user_info").permitAll()
            .antMatchers(HttpMethod.GET, "/api/private-scoped").hasAuthority("read:messages");
    }

    @Bean
    public TokenDTO tokenDTO() {return new TokenDTO(clientId, clientSecret, apiAudience);}

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


