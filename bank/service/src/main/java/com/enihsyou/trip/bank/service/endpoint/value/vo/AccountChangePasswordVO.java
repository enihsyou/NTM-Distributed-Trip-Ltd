package com.enihsyou.trip.bank.service.endpoint.value.vo;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountChangePasswordVO {

    String username;

    boolean existence;
}
