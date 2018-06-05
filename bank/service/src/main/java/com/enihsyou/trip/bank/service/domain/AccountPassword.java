package com.enihsyou.trip.bank.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class AccountPassword extends MySQLAbstractPersistable<Long> {

    /**
     * Who the password history belong to.
     */
    @OneToOne
    private Account account;

    /**
     * BCrypt hashed password
     */
    private String password;

    /**
     * Point to last used password, if exist, to construct a password history chain.
     */
    @OneToOne
    private AccountPassword lastPassword;
}
