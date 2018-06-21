package com.enihsyou.trip.bank.service.endpoint.value.vo;

import com.enihsyou.trip.bank.service.domain.Transaction;
import com.enihsyou.trip.bank.service.domain.TransactionCategory;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Value
public class TransactionVO {

    Long transaction_id;

    TransactionCategory category;

    String description;

    BigDecimal amount;

    BigDecimal amountAfter;

    Instant created_time;

    public static TransactionVO parse(Transaction t) {
        return new TransactionVO(t.getId(), t.getCategory(), t.getDescription(), t.getBalanceRecord().getAmount(),
            t.getBalanceRecord().getBalanceAfter(),
            t.getCreatedTime());
    }
}

