package com.enihsyou.trip.bank.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Transaction extends MySQLAbstractPersistable<Long> {

    @ManyToOne
    private Account account;

    @Embedded
    private BalanceRecord balanceRecord;

    /**
     * Short explanation of "Why does this transaction happened".
     */
    private String explanation;

    /**
     * Time of the transaction occurrence.
     */
    private Instant eventTime;

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
        BigDecimal balanceAfter;
    }
}

