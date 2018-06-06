package com.enihsyou.trip.bank.service.endpoint;

import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface BankAccountEndpoint {


    /**
     * Given the access token obtained during login, this endpoint returns a user's profile.
     * <p>
     * User's transactions history is also included.
     *
     * @return
     */
    @GetMapping("user_info")
    ResponseEntity<AccountDetailVO> accountDetail();

}

interface BankTransactionEndpoint {

    @GetMapping("transactions")
    ResponseEntity<AccountDetailVO> accountTransactionDetails();

    @PostMapping("transactions")
    ResponseEntity startNewTransaction();

    @PutMapping("transactions/{transactionId}")
    ResponseEntity commitTransaction(@PathVariable("transactionId") String transactionId);
}

interface BankBaseEndpoint {

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
     * Given a user's username, it's old password and a new password in plain text format,
     * this endpoint will change user's password, and log account password history.
     *
     * @return
     */
    @PostMapping("username/change_password")
    ResponseEntity changeAccountPassword();
}

interface BankAuthorizeEndpoint{

    /**
     * Use this endpoint to authenticate a user using password as credential.
     * @return
     */
    @GetMapping("oauth/authorize")
    ResponseEntity loginUser();

    @PostMapping("oauth/token")
    ResponseEntity grantAuthorizationCode();
}
