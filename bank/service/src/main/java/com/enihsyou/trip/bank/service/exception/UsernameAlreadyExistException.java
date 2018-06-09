package com.enihsyou.trip.bank.service.exception;

public class UsernameAlreadyExistException extends BaseRestException {

    @Override
    public String getMessage() {
        return "用户名已存在";
    }
}

