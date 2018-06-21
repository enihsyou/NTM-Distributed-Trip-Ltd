package com.enihsyou.trip.bank.service.service.impl;

import com.enihsyou.trip.bank.service.command.transaction.PaymentTransactionCommand;
import com.enihsyou.trip.bank.service.command.transaction.TransactionCommand;
import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Order;
import com.enihsyou.trip.bank.service.domain.Transaction;
import com.enihsyou.trip.bank.service.domain.TransactionCategory;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountAuthorizeDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountChangePasswordDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountSignupDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.OrderCreateDTO;
import com.enihsyou.trip.bank.service.exception.*;
import com.enihsyou.trip.bank.service.helper.SecurityHelper;
import com.enihsyou.trip.bank.service.repository.AccountRepository;
import com.enihsyou.trip.bank.service.repository.OrderRepository;
import com.enihsyou.trip.bank.service.repository.TransactionRepository;
import com.enihsyou.trip.bank.service.service.BankService;
import com.enihsyou.trip.bank.service.service.auth0.Auth0ApiService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;

@Service
public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    private final OrderRepository orderRepository;

    private final TransactionRepository transactionRepository;

    private final Auth0ApiService authService;

    private final PasswordEncoder passwordEncoder;

    public BankServiceImpl(AccountRepository accountRepository,
                           OrderRepository orderRepository,
                           TransactionRepository transactionRepository,
                           Auth0ApiService authService,
                           PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.orderRepository = orderRepository;
        this.transactionRepository = transactionRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account signupAccount(final AccountSignupDTO signupDTO) {
        final String username = signupDTO.getUsername();
        final String password = signupDTO.getPassword();

        if (accountExistence(username)) throw new UsernameAlreadyExistException();

        String userId = authService.signupUserForUserId(username, password);
        final Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setUserId(userId);

        accountRepository.save(account);

        return account;
    }

    @Override
    public boolean accountExistence(final String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Account authorizeAccount(final AccountAuthorizeDTO authorizeDTO) {
        final String username = authorizeDTO.getUsername();
        final String password = authorizeDTO.getPassword();

        final Account account = obtainAccountByUsername(username);
        final boolean matches = isPasswordMatches(password, account);
        if (!matches) throw new WrongCredientialException();

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(username, password,
                Collections.singletonList(new SimpleGrantedAuthority("USER")));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return account;
    }

    private Account obtainAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
            .orElseThrow(UsernameNotFoundException::new);
    }

    private boolean isPasswordMatches(String password, Account account) {
        return passwordEncoder.matches(password, account.getPassword());
    }

    @Override
    public Account changeAccountPassword(final AccountChangePasswordDTO changePasswordDTO) {
        final String oldPassword = changePasswordDTO.getOld_password();
        final String newPassword = changePasswordDTO.getNew_password();
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final Account account = obtainAccountByUsername(username);
        final boolean matches = isPasswordMatches(oldPassword, account);
        if (!matches) throw new WrongCredientialException();

        account.setPassword(passwordEncoder.encode(newPassword));
        return account;
    }

    @Override
    public Account detailAccount() {
        return obtainAccount();
    }

    private Account obtainAccount() {
        final String userId = SecurityHelper.userId();
        return obtainAccountByUserId(userId);
    }

    private Account obtainAccountByUserId(String userId) {
        return accountRepository.findByUserId(userId)
            .orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public Transaction makeTransaction(TransactionCategory command, BigDecimal amount) {
        final Account account = obtainAccount();
        final TransactionCommand transactionCommand = command.toCommand(account, amount);
        transactionCommand.execute();

        final Transaction transaction = transactionCommand.getTransaction();
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Account detailTransaction() {
        return obtainAccount();
    }

    @Override
    public Transaction detailTransaction(Long transactionId) {
        return obtainAccount().getTransactions()
            .stream()
            .filter(transaction -> transaction.getId().equals(transactionId))
            .findAny()
            .orElseThrow(TransactionNotExistException::new);
    }

    @Override
    public Account detailOrder() {
        return obtainAccount();
    }

    @Override
    public Order detailOrder(Long orderId) {
        return obtainAccount().getOrders()
            .stream()
            .filter(order -> order.getId().equals(orderId))
            .findAny()
            .orElseThrow(OrderNotExistException::new);
    }

    @Override
    public Order createOrder(OrderCreateDTO createDTO) {
        final Account account = obtainAccount();
        final Order order = new Order();
        order.setAccount(account);
        order.setAmount(createDTO.getAmount());
        order.setDescription(createDTO.getDescription());
        orderRepository.save(order);
        return order;
    }

    @Override
    public void cancelOrder(Long orderId) {
        final Order order = obtainOrder(orderId);
        order.setCommittedTime(Instant.now());
    }

    private Order obtainOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotExistException::new);
    }

    @Override
    public void confirmOrder(Long orderId) {
        final Order order = obtainOrder(orderId);

        TransactionCommand command = new PaymentTransactionCommand(order);
        command.execute();
        order.setTransaction(command.getTransaction());
        order.setCommittedTime(Instant.now());
    }
}

