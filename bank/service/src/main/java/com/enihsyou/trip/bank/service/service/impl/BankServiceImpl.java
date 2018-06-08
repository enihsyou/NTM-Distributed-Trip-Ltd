package com.enihsyou.trip.bank.service.service.impl;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountAuthorizeDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountChangePasswordDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountSignupDTO;
import com.enihsyou.trip.bank.service.exception.UsernameAlreadyExistException;
import com.enihsyou.trip.bank.service.exception.UsernameNotFoundException;
import com.enihsyou.trip.bank.service.exception.WrongCredientialException;
import com.enihsyou.trip.bank.service.repository.AccountRepository;
import com.enihsyou.trip.bank.service.service.BankService;
import com.enihsyou.trip.bank.service.service.auth0.Auth0ApiService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    private final Auth0ApiService authService;

    private final PasswordEncoder passwordEncoder;

    public BankServiceImpl(AccountRepository accountRepository,
                           Auth0ApiService authService,
                           PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean accountExistence(final String username) {
        return accountRepository.existsByUsername(username);
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
    public Account authorizeAccount(final AccountAuthorizeDTO authorizeDTO) {
        final String username = authorizeDTO.getUsername();
        final String password = authorizeDTO.getPassword();

        final Account account = obtainAccount(username);
        final boolean matches = isPasswordMatches(password, account);
        if (!matches) throw new WrongCredientialException();

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(username, password,
                Arrays.asList(new SimpleGrantedAuthority("USER")));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return account;
    }

    @Override
    public Account changeAccountPassword(final AccountChangePasswordDTO changePasswordDTO) {
        final String oldPassword = changePasswordDTO.getOld_password();
        final String newPassword = changePasswordDTO.getNew_password();
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final Account account = obtainAccount(username);
        final boolean matches = isPasswordMatches(oldPassword, account);
        if (!matches) throw new WrongCredientialException();

        account.setPassword(passwordEncoder.encode(newPassword));
        return account;
    }

    private Account obtainAccount(String username) {
        return accountRepository.findByUsername(username)
            .orElseThrow(UsernameNotFoundException::new);
    }

    private boolean isPasswordMatches(String password, Account account) {
        return passwordEncoder.matches(password, account.getPassword());
    }
}

