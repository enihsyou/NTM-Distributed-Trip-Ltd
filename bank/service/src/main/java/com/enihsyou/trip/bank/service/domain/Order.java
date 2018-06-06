package com.enihsyou.trip.bank.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Order extends MySQLAbstractPersistable<Long>{

    @ManyToOne
    private Account account;

    private String description;

    private BigDecimal amount;
}
