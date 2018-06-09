package com.enihsyou.trip.bank.service.exception;

public class TransactionNotExistException extends BaseRestException {

    @Override
    public String getMessage() {
        return "交易号不存在";
    }
}

