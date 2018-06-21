package com.enihsyou.trip.bank.service.endpoint.value.vo;

import com.enihsyou.trip.bank.service.domain.Account;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Value
public class AccountDetailVO {

    String username;

    Long account_id;

    BigDecimal balance;

    List<TransactionVO> transactions;

    List<OrderVO>       orders;

    public static AccountDetailVO parse(Account account) {
        return builder()
            .username(account.getUsername())
            .account_id(account.getId())
            .balance(account.getBalance())
            .transactions(account.getTransactions().stream().map(TransactionVO::parse).collect(Collectors.toList()))
            .orders(account.getOrders().stream().map(OrderVO::parse).collect(Collectors.toList()))
            .build();
    }
}
