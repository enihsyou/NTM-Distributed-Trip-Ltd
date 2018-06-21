package com.enihsyou.trip.bank.service.command.transaction;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Transaction;
import com.enihsyou.trip.bank.service.domain.Transaction.BalanceRecord;
import com.enihsyou.trip.bank.service.domain.TransactionCategory;
import com.enihsyou.trip.bank.service.exception.IllegalAmountException;

import java.math.BigDecimal;

public class DepositTransactionCommand extends TransactionCommand {

    private BigDecimal amount;

    public DepositTransactionCommand(Account account, BigDecimal amount) {
        super(account);
        this.amount = amount;
    }

    @Override
    public void execute() {
        if (amount.signum() < 0) throw new IllegalAmountException();
        final BalanceRecord balanceRecord = new BalanceRecord(account.getBalance(), amount);

        account.setBalance(account.getBalance().add(amount));

        transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCategory(TransactionCategory.Deposit);
        transaction.setDescription(String.format("存入%s元", formatter.format(amount)));
        transaction.setBalanceRecord(balanceRecord);
    }

}

