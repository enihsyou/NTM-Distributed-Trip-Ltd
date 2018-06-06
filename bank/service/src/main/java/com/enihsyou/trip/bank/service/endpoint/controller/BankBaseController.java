package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.endpoint.BankBaseEndpoint;
import org.springframework.http.ResponseEntity;

public class BankBaseController implements BankBaseEndpoint {

    @Override
    public ResponseEntity accountExistence() {
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
