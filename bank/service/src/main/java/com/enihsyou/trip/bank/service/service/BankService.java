package com.enihsyou.trip.bank.service.service;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountAuthorizeDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountChangePasswordDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountSignupDTO;

public interface BankService {

    boolean accountExistence(String username);

    Account signupAccount(AccountSignupDTO signupDTO);

    Account authorizeAccount(AccountAuthorizeDTO authorizeDTO);

    Account changeAccountPassword(AccountChangePasswordDTO changePasswordDTO);

    Account detailAccount(String userId);
}

