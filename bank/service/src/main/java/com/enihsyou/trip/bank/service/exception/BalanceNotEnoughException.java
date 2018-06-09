package com.enihsyou.trip.bank.service.exception;

public class BalanceNotEnoughException extends BaseRestException {

    @Override
    public String getMessage() {
        return "余额不足";
    }
}

