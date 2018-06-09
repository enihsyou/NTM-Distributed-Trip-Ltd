package com.enihsyou.trip.bank.service.service.auth0;

import com.enihsyou.trip.bank.service.config.SecurityConfig.TokenDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public Auth0ApiServiceImpl(@Qualifier("auth0RestTemplate") RestTemplate template,
                               TokenDTO tokenDTO,
                               @Autowired(required = false) @Qualifier("accessToken") String ACCESS_CODE) {
        this.template = template;
        this.tokenDTO = tokenDTO;
        final String reason;
        final String accessToken;
        if (ACCESS_CODE == null) {
            accessToken = refreshCode();
            reason = "Got Auth0 access token";
        } else {
            accessToken = ACCESS_CODE;
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
        this.ACCESS_CODE = "Bearer " + accessToken;
        log.info("{}, token first 36 characters are: {}", reason, safeDisplayAccessToken());
    }

    private String refreshCode() {
        URI uri = URI.create("https://enihsyou.auth0.com/oauth/token");
        final RequestEntity<TokenDTO> request = RequestEntity.post(uri)
            .contentType(MediaType.APPLICATION_JSON_UTF8).body(tokenDTO);

        log.info("Try to get Auth0 access token, client id: {}", tokenDTO.getClient_id());
        final ObjectNode response = template.exchange(request, ObjectNode.class).getBody();

        return response.get("access_token").textValue();
    }

    private String safeDisplayAccessToken() {
        //noinspection MagicNumber
        return ACCESS_CODE.substring(0, 36);
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
