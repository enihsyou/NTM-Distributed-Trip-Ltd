package com.enihsyou.trip.bank.service.exception;

public class UsernameNotFoundException extends BaseRestException {

    @Override
    public String getMessage() {
        return "用户名不存在";
    }
}


