package com.enihsyou.trip.bank.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Order extends MySQLAbstractPersistable<Long> {

    @ManyToOne
    private Account account;

    /**
     * Short description of this order.
     */
    private String description;

    /**
     * The transaction amount of this order.
     */
    private BigDecimal amount;

    /**
     * Time when this order create.
     */
    private Instant createdTime = Instant.now();

    /**
     * Time when this order finished.
     * <p>
     * Whether is canceled or paid.
     */
    private Instant committedTime;

    /**
     * Order associated transaction.
     * <p>
     * field is null if there is no transaction associated of this order.
     */
    @OneToOne
    private Transaction transaction;

    public boolean isCanceled() {
        return !isPending() && transaction == null;
    }

    public boolean isPending() {
        return committedTime == null;
    }

    public boolean isPaid() {
        return !isPending() && transaction != null;
    }
}
