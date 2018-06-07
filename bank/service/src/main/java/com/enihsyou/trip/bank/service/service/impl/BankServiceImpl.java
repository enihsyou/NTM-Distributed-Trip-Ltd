package com.enihsyou.trip.bank.service.service.impl;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountAuthorizeDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountChangePasswordDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountSignupDTO;
import com.enihsyou.trip.bank.service.repository.AccountRepository;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public BankServiceImpl(final AccountRepository accountRepository,
                           final PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean accountExistence(final String username) {

        final Account probe = new Account();
        probe.setUsername(username);
        return accountRepository.exists(Example.of(probe));
    }

    @Override
    public Account signupAccount(final AccountSignupDTO signupDTO) {
        final String username = signupDTO.getUsername();
        final String password = signupDTO.getPassword();

        final Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));

        accountRepository.save(account);
        return account;
    }

    @Override
    public Account authorizeAccount(final AccountAuthorizeDTO authorizeDTO) {
        final String username = authorizeDTO.getUsername();
        final String password = authorizeDTO.getPassword();

        final Account account = accountRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        final boolean matches = passwordEncoder.matches(password, account.getPassword().getPassword());
        if (matches) {
            return account;
        }
        throw new RuntimeException();
    }

    @Override
    public Account changeAccountPassword(final AccountChangePasswordDTO changePasswordDTO) {
        final String oldPassword = changePasswordDTO.getOld_password();
        final String newPassword = changePasswordDTO.getNew_password();
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final Account account = accountRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        return null;
    }
}
