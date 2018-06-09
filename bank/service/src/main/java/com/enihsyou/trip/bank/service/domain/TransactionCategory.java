package com.enihsyou.trip.bank.service.domain;

import com.enihsyou.trip.bank.service.command.transaction.DepositTransactionCommand;
import com.enihsyou.trip.bank.service.command.transaction.TransactionCommand;
import com.enihsyou.trip.bank.service.command.transaction.WithdrawTransactionCommand;

import java.math.BigDecimal;

public enum TransactionCategory {
    Deposit, Withdraw, Payment;

    public TransactionCommand toCommand(Account account, BigDecimal amount) {
        switch (this) {
            case Deposit: return new DepositTransactionCommand(account, amount);
            case Withdraw: return new WithdrawTransactionCommand(account, amount);
            default: throw new IllegalArgumentException(this.toString());
        }
    }
}
