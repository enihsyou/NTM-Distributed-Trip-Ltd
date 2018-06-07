package com.enihsyou.trip.bank.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Transaction extends MySQLAbstractPersistable<Long> {

    @ManyToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionCategory category;

    @Embedded
    private BalanceRecord balanceRecord;

    /**
     * Short explanation of "Why does this transaction happened".
     */
    private String description;

    /**
     * Time of the transaction created.
     */
    private Instant createdTime = Instant.now();

    @Embeddable
    static class BalanceRecord {

        /**
         * Account balance before this transaction.
         */
        BigDecimal balanceBefore;

        /**
         * The amount of this transaction.
         */
        BigDecimal amount;

        /**
         * Account balance after this transaction.
         */
        BigDecimal balanceAfter() {
            return balanceBefore.add(amount);
        }
    }
}
