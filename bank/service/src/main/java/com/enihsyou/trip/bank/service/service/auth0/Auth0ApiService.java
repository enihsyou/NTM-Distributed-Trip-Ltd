package com.enihsyou.trip.bank.service.service.auth0;

public interface Auth0ApiService {

    String signupUserForUserId(String email, String rawPassword);
}
