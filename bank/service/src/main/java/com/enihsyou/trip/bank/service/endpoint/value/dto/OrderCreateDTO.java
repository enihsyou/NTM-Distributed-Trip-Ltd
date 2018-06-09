package com.enihsyou.trip.bank.service.endpoint.value.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderCreateDTO {

    String description;

    String retailer;

    BigDecimal amount;
}
