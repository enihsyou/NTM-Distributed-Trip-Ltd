package com.enihsyou.trip.bank.service.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account extends MySQLAbstractPersistable<Long> {

    @NaturalId
    private String username;

    /**
     * Current password of this account.
     * <p>
     * password is BCrypt hashed.
     */
    @OneToOne(mappedBy = "account")
    private AccountPassword password;

    /**
     * Transactions this account has made.
     */
    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();

    /**
     * Current account balance.
     */
    private BigDecimal balance = BigDecimal.ZERO;

    public Account setPassword(String password) {
        AccountPassword newPassword = new AccountPassword();
        newPassword.setPassword(password);
        newPassword.setLastPassword(this.password);
        newPassword.setAccount(this);

        this.password = newPassword;
        return this;
    }
}


