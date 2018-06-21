package com.enihsyou.trip.bank.service.service.auth0;

import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class Auth0ApiServiceImpl implements Auth0ApiService {

    private final RestTemplate template;

    @Autowired
    public Auth0ApiServiceImpl(@Qualifier("auth0RestTemplate") RestTemplate template) { this.template = template;}


    @Override
    public String signupUserForUserId(String email, String rawPassword) {
        final URI uri = URI.create("https://enihsyou.auth0.com/api/v2/users");

        final SignupUserDTO body = new SignupUserDTO(email, rawPassword);

        final ResponseEntity<SignupUserVO> response;
        try {
            response = template.postForEntity(uri, body, SignupUserVO.class);
            return response.getBody().getUser_id();
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
return "";
    }

    @Value
    private static class SignupUserDTO {

        String connection = "Bank";

        String email, password;

        boolean email_verified = true;
    }

    @Data
    private static class SignupUserVO {

        String user_id;

    }
}
