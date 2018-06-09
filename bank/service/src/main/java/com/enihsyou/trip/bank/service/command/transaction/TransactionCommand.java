package com.enihsyou.trip.bank.service.command.transaction;

import com.enihsyou.trip.bank.service.command.Command;
import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Transaction;
import lombok.Getter;

import java.text.NumberFormat;

@Getter
public abstract class TransactionCommand implements Command {

    protected Account account;

    protected Transaction transaction;

    protected NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public TransactionCommand(Account account) {
        this.account = account;
    }
}

