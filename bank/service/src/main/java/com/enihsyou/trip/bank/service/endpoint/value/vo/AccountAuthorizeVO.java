package com.enihsyou.trip.bank.service.endpoint.value.vo;


import lombok.Builder;

@Builder
public class AccountAuthorizeVO {
    String username;
    boolean existence;
}