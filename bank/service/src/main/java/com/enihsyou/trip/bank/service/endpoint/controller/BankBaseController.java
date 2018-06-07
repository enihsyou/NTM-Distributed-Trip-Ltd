package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.endpoint.BankBaseEndpoint;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankBaseController implements BankBaseEndpoint {

    private final BankService bankService;

    public BankBaseController(final BankService bankService) {this.bankService = bankService;}

    @Override
    public ResponseEntity accountExistence(final String testUsername) {
        return null;
    }

    @Override
    public ResponseEntity signupAccount() {
        return null;
    }

    @Override
    public ResponseEntity authorizeAccount() {
        return null;
    }

    @Override
    public ResponseEntity changeAccountPassword() {
        return null;
    }
}
