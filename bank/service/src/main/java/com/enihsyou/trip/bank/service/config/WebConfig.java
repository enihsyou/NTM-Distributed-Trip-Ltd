package com.enihsyou.trip.bank.service.config;

import com.enihsyou.trip.bank.service.config.Auth0PropertiesLoader.TokenDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URI;

@Slf4j
@Configuration
@Import(Auth0PropertiesLoader.class)
public class WebConfig implements WebMvcConfigurer {

    private final String ACCESS_CODE;

    private final TokenDTO tokenDTO;

    @Autowired
    public WebConfig(@Qualifier("accessToken") String accessCode, TokenDTO tokenDTO) {
        this.tokenDTO = tokenDTO;
        this.ACCESS_CODE = "Bearer " + parseAccessCode(accessCode);
    }

    private String parseAccessCode(@Qualifier("accessToken") String accessCode) {
        final String reason;
        final String accessToken;
        if (accessCode == null || accessCode.isEmpty()) {
            accessToken = refreshCode();
            reason = "Got Auth0 access token";
        } else {
            accessToken = accessCode;
            // Verify the token
            // try {
            //     final Algorithm algorithm = Algorithm.HMAC256(tokenDTO.getClient_secret());
            //     final JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
            //     verifier.verify(accessToken);
            // } catch (UnsupportedEncodingException e) {
            //     e.printStackTrace();
            //     System.out.println("Test");
            //     throw new RuntimeException(e.getCause());
            // }catch (JWTVerificationException e){
            //     e.printStackTrace();
            // }
            reason = "Use exist Auth0 access token";
        }
        log.info("{}, token first 36 characters are: {}", reason, safeDisplayToken(accessToken));
        return accessToken;
    }

    private String refreshCode() {
        URI uri = URI.create("https://enihsyou.auth0.com/oauth/token");
        final RequestEntity<TokenDTO> request = RequestEntity.post(uri)
            .contentType(MediaType.APPLICATION_JSON_UTF8).body(tokenDTO);

        log.info("Try to get Auth0 access token, client id: {}", tokenDTO.getClient_id());
        final ObjectNode response = new RestTemplate().exchange(request, ObjectNode.class).getBody();

        return response.get("access_token").textValue();
    }

    private static String safeDisplayToken(String accessCode) {
        //noinspection MagicNumber
        return accessCode.substring(0, Math.min(accessCode.length(), 36));
    }

    @Bean
    public RestTemplate auth0RestTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder
            .additionalInterceptors((request, body, execution) -> {
                request.getHeaders().set("Authorization", this.ACCESS_CODE);
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                return execution.execute(request, body);
            })
            .build();
    }

}
