package com.enihsyou.trip.bank.service.endpoint.value.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreateDTO {

    String description;

    String retailer;

    BigDecimal amount;
}
