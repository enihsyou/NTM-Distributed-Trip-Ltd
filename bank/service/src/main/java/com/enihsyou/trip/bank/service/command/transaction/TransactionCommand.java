package com.enihsyou.trip.bank.service.command.transaction;

import com.enihsyou.trip.bank.service.command.Command;
import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Transaction;
import lombok.Getter;

@Getter
public abstract class TransactionCommand implements Command {

    protected Account account;
    protected Transaction transaction;
}

