package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.endpoint.BankTransactionEndpoint;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankTransactionController implements BankTransactionEndpoint {

    private final BankService bankService;

    public BankTransactionController(final BankService bankService) {this.bankService = bankService;}

    @Override
    public ResponseEntity<AccountDetailVO> accountTransactionDetails() {
        return null;
    }

    @Override
    public ResponseEntity startTransaction(final String command) {
        return null;
    }

    @Override
    public ResponseEntity detailTransaction(final String transactionId) {
        return null;
    }
}
