package com.enihsyou.trip.bank.service.service.auth0;

import com.enihsyou.trip.bank.service.config.SecurityConfig.TokenDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class Auth0ApiServiceImpl implements Auth0ApiService {

    private final RestTemplate template;

    private final TokenDTO tokenDTO;

    private String ACCESS_CODE;

    public Auth0ApiServiceImpl(@Qualifier("auth0RestTemplate") RestTemplate template,
                               TokenDTO tokenDTO) {
        this.template = template;
        this.tokenDTO = tokenDTO;
        refreshCode();
    }

    private void refreshCode() {
        URI uri = URI.create("https://enihsyou.auth0.com/oauth/token");
        final RequestEntity<TokenDTO> request = RequestEntity.post(uri)
            .contentType(MediaType.APPLICATION_JSON_UTF8).body(tokenDTO);

        log.info("Try to get Auth0 access token, client id: {}", tokenDTO.getClient_id());
        final ObjectNode response = template.exchange(request, ObjectNode.class).getBody();

        final String tokenType = response.get("token_type").textValue();
        final String accessToken = response.get("access_token").textValue();

        ACCESS_CODE = tokenType + ' ' + accessToken;
        log.info("Got Auth0 access token: {}", accessToken);
    }

    @Override
    public String signupUserForUserId(String email, String rawPassword) {
        final URI uri = URI.create("users");

        final SignupUserDTO body = new SignupUserDTO(email, rawPassword);

        RequestEntity<SignupUserDTO> request = requestFor(HttpMethod.POST, uri).body(body);

        final ResponseEntity<SignupUserVO> response = template.exchange(request, SignupUserVO.class);

        return response.getBody().getUser_id();
    }

    private BodyBuilder requestFor(HttpMethod method, final URI uri) {
        return RequestEntity.method(method, uri)
            .header("Authorization", ACCESS_CODE)
            .contentType(MediaType.APPLICATION_JSON_UTF8);
    }


    @Value
    private static class SignupUserDTO {

        String connection = "Bank";

        String email, password;

        boolean email_verified = true;
    }

    @Value
    private static class SignupUserVO {

        String user_id;

    }
}
