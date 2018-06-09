package com.enihsyou.trip.bank.service.service;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Order;
import com.enihsyou.trip.bank.service.domain.Transaction;
import com.enihsyou.trip.bank.service.domain.TransactionCategory;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountAuthorizeDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountChangePasswordDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountSignupDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.OrderCreateDTO;

import java.math.BigDecimal;

public interface BankService {

    boolean accountExistence(String username);

    Account signupAccount(AccountSignupDTO signupDTO);

    Account authorizeAccount(AccountAuthorizeDTO authorizeDTO);

    Account changeAccountPassword(AccountChangePasswordDTO changePasswordDTO);

    Account detailAccount();

    Transaction makeTransaction(TransactionCategory command, BigDecimal amount);

    Account detailTransaction();

    Transaction detailTransaction(Long transactionId);

    Order createOrder(OrderCreateDTO createDTO);

    Account detailOrder();

    Order detailOrder(Long orderId);

    void cancelOrder(Long orderId);

    void confirmOrder(Long orderId);
}

