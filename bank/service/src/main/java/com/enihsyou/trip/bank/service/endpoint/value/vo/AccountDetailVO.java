package com.enihsyou.trip.bank.service.endpoint.value.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDetailVO {

    String username;

    Long account_id;
}
