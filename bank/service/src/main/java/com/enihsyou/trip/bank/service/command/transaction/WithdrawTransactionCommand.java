package com.enihsyou.trip.bank.service.command.transaction;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Transaction;
import com.enihsyou.trip.bank.service.domain.Transaction.BalanceRecord;
import com.enihsyou.trip.bank.service.domain.TransactionCategory;
import com.enihsyou.trip.bank.service.exception.BalanceNotEnoughException;

import java.math.BigDecimal;

public class WithdrawTransactionCommand extends TransactionCommand {

    private BigDecimal amount;

    public WithdrawTransactionCommand(Account account, BigDecimal amount) {
        super(account);
        this.amount = amount;
    }

    @Override
    public void execute() {
        final BigDecimal balanceBefore = account.getBalance();
        final BigDecimal probe = balanceBefore.subtract(amount);
        final BalanceRecord balanceRecord = new BalanceRecord(balanceBefore, amount.negate());
        if (probe.signum() >= 0) {
            account.setBalance(probe);
        } else throw new BalanceNotEnoughException();

        transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCategory(TransactionCategory.Withdraw);
        transaction.setDescription(String.format("取出%s元", formatter.format(amount)));
        transaction.setBalanceRecord(balanceRecord);
    }
}
