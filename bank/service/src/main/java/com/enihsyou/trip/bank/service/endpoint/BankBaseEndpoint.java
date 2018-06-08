package com.enihsyou.trip.bank.service.endpoint;

import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountAuthorizeDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountChangePasswordDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountSignupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("username")
public interface BankBaseEndpoint {

    /**
     * Given a username in string format, check its existence.
     */
    @GetMapping("challenge")
    ResponseEntity accountExistence(@RequestParam("test") String testUsername);

    /**
     * Given a user's credentials, this endpoint will create a new user using active authentication.
     * <p>
     * Credential in field {@link AccountSignupDTO#password} is in plain text format.
     */
    @PostMapping("signup")
    ResponseEntity signupAccount(@RequestBody AccountSignupDTO signupDTO);

    /**
     * Use this endpoint to authenticate a user using password as credential.
     */
    @PostMapping("authorize")
    ResponseEntity authorizeAccount(@RequestBody AccountAuthorizeDTO authorizeDTO);

    /**
     * Given a user's username, it's old password and a new password in plain text format,
     * this endpoint will change user's password, and log account password history.
     */
    @PostMapping("change_password")
    ResponseEntity changeAccountPassword(@RequestBody AccountChangePasswordDTO changePasswordDTO);
}
