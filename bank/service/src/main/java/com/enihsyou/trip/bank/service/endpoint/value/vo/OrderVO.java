package com.enihsyou.trip.bank.service.endpoint.value.vo;

import com.enihsyou.trip.bank.service.domain.Order;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Value
public class OrderVO {

    Long order_id;

    String description;

    BigDecimal amount;

    Instant created_time;

    Instant committed_time;

    boolean is_committed;

    Long transaction_id;

    public static OrderVO parse(Order o) {
        return builder()
            .order_id(o.getId())
            .description(o.getDescription())
            .amount(o.getAmount())
            .created_time(o.getCreatedTime())
            .committed_time(o.getCommittedTime())
            .is_committed(o.getCommittedTime() != null)
            .build();
    }
}
