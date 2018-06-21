package com.enihsyou.trip.bank.service.endpoint.value.dto;

import lombok.Data;

@Data
public class AccountChangePasswordDTO {

    String old_password;

    String new_password;
}
