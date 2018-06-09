package com.enihsyou.trip.bank.service.exception;

public class OrderNotExistException extends BaseRestException {

    @Override
    public String getMessage() {
        return "订单号不存在";
    }
}
