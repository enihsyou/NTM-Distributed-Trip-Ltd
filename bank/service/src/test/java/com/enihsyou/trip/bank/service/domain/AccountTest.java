package com.enihsyou.trip.bank.service.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AccountTest {

    @Autowired
    private TestEntityManager manager;

    @Test
    public void test() {

        Account account = new Account();
        account.setUsername("ABC");
        manager.persist(account);

        final Account account1 = manager.find(Account.class, account.getId());
        assertThat(account1).isEqualTo(account);
    }

}

