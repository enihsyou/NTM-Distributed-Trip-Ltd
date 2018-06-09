package com.enihsyou.trip.bank.service.exception;

public class IllegalAmountException extends BaseRestException {

    @Override
    public String getMessage() {
        return "参数不正确";
    }
}
