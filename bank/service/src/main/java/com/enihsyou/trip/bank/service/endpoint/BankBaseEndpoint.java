package com.enihsyou.trip.bank.service.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface BankBaseEndpoint {

    /**
     * Given a username in string format, check its existence.
     *
     * @return
     */
    @GetMapping("username/challenge")
    ResponseEntity accountExistence();

    /**
     * Given a user's credentials, this endpoint will create a new user using active authentication.
     *
     * @return
     */
    @PostMapping("username/signup")
    ResponseEntity signupAccount();

    /**
     * Use this endpoint to authenticate a user using password as credential.
     *
     * @return
     */
    @PostMapping("username/authorize")
    ResponseEntity authorizeAccount();

    /**
     * Given a user's username, it's old password and a new password in plain text format,
     * this endpoint will change user's password, and log account password history.
     *
     * @return
     */
    @PostMapping("username/change_password")
    ResponseEntity changeAccountPassword();
}
