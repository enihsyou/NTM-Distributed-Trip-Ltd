package com.enihsyou.trip.bank.service.command.transaction;

import com.enihsyou.trip.bank.service.domain.Order;
import com.enihsyou.trip.bank.service.domain.Transaction;
import com.enihsyou.trip.bank.service.domain.Transaction.BalanceRecord;
import com.enihsyou.trip.bank.service.domain.TransactionCategory;
import com.enihsyou.trip.bank.service.exception.IllegalAmountException;

import java.math.BigDecimal;

public class PaymentTransactionCommand extends TransactionCommand {

    private BigDecimal amount;

    private Long orderId;

    public PaymentTransactionCommand(Order order) {
        super(order.getAccount());
        this.amount = order.getAmount();
        this.orderId = order.getId();
    }

    @Override
    public void execute() {
        if (amount.signum() < 0) throw new IllegalAmountException();
        final BalanceRecord balanceRecord = BalanceRecord.builder()
            .balanceBefore(account.getBalance())
            .amount(amount)
            .build();

        account.setBalance(account.getBalance().add(amount));

        transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCategory(TransactionCategory.Deposit);
        transaction.setDescription(String.format("在订单%d消费%s元", orderId, formatter.format(amount)));
        transaction.setBalanceRecord(balanceRecord);
    }

}

