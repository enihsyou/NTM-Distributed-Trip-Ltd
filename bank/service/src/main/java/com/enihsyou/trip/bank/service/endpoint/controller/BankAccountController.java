package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.endpoint.BankAccountEndpoint;
import com.enihsyou.trip.bank.service.endpoint.BankTransactionEndpoint;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController implements BankAccountEndpoint {

    private final BankService bankService;

    public BankAccountController(final BankService bankService) {this.bankService = bankService;}

    @Override
    public ResponseEntity<AccountDetailVO> accountDetail() {
        return null;
    }
}

