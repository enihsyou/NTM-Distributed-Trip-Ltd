package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.endpoint.BankTransactionEndpoint;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import org.springframework.http.ResponseEntity;

public class BankTransactionController implements BankTransactionEndpoint {

    @Override
    public ResponseEntity<AccountDetailVO> accountTransactionDetails() {
        return null;
    }

    @Override
    public ResponseEntity startNewTransaction() {
        return null;
    }

    @Override
    public ResponseEntity detailTransaction(final String transactionId) {
        return null;
    }

    @Override
    public ResponseEntity modifyTransaction(final String transactionId) {
        return null;
    }

    @Override
    public ResponseEntity commitTransaction(final String transactionId) {
        return null;
    }
}
