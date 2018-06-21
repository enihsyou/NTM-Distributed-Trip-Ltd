package com.enihsyou.trip.bank.service.service;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.repository.AccountRepository;
import com.enihsyou.trip.bank.service.repository.OrderRepository;
import com.enihsyou.trip.bank.service.repository.TransactionRepository;
import com.enihsyou.trip.bank.service.service.auth0.Auth0ApiService;
import com.enihsyou.trip.bank.service.service.auth0.Auth0ApiServiceImpl;
import com.enihsyou.trip.bank.service.service.impl.BankServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Auth0ApiServiceImpl.class)
public class BankServiceTest {

    @MockBean
    private AccountRepository     mockAccountRepository;

    @MockBean
    private OrderRepository       orderRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private Auth0ApiService       authService;

    private BankService           bankService;

    @Before
    public void setUp() {
        bankService =
            new BankServiceImpl(mockAccountRepository, orderRepository, transactionRepository, authService,
                new BCryptPasswordEncoder());
    }

    @Test
    public void accountExistence() {
        String testUsername = "111@a.com";
        given(mockAccountRepository.findByUsername(testUsername)).willReturn(Optional.empty());

        final boolean existence = bankService.accountExistence(testUsername);
        assertThat(existence).isFalse();

        given(mockAccountRepository.findByUsername(testUsername)).willReturn(
            Optional.of(new Account(testUsername)));

        final boolean existence2 = bankService.accountExistence(testUsername);
        assertThat(existence2).isTrue();
    }

    @Test
    public void signupAccount() {
        final String testUsername = "111@a.com";
        final String testPassword = "111";
    }

    @Test
    public void authorizeAccount() {
    }

    @Test
    public void changeAccountPassword() {
    }
}
