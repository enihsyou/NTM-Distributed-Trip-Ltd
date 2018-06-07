package com.enihsyou.trip.bank.service.endpoint.value.dto;

import lombok.Value;

@Value
public class AccountChangePasswordDTO {

    String old_password;

    String new_password;
}
