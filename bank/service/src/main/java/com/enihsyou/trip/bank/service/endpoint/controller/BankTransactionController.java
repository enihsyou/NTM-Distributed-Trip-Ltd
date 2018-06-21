package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Transaction;
import com.enihsyou.trip.bank.service.domain.TransactionCategory;
import com.enihsyou.trip.bank.service.endpoint.BankTransactionEndpoint;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import com.enihsyou.trip.bank.service.endpoint.value.vo.TransactionVO;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BankTransactionController implements BankTransactionEndpoint {

    private final BankService bankService;

    public BankTransactionController(final BankService bankService) {this.bankService = bankService;}

    @Override
    public ResponseEntity accountTransactionDetails() {
        // AuthorityType.ReadTransaction.shouldGrant();
        Account account = bankService.detailTransaction();
        final AccountDetailVO body = AccountDetailVO.parse(account);
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity startTransaction(TransactionCategory command, BigDecimal amount) {
        // AuthorityType.WriteTransaction.shouldGrant();
        Transaction transaction = bankService.makeTransaction(command, amount);

        return ResponseEntity.ok(TransactionVO.parse(transaction));
    }

    @Override
    public ResponseEntity detailTransaction(final String transactionId) {
        //AuthorityType.ReadTransaction.shouldGrant();
        Transaction transaction = bankService.detailTransaction(Long.parseLong(transactionId));
        return ResponseEntity.ok(TransactionVO.parse(transaction));
    }
}
