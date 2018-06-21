package com.enihsyou.trip.bank.service.endpoint.value.vo;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountMakeTransactionVO {

    String username;

    Long account_id;
}

