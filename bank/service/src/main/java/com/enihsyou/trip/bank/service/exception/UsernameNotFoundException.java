package com.enihsyou.trip.bank.service.exception;

public class UsernameNotFoundException extends BaseRestException {

    @Override
    public String getMessage() {
        return "错误的用户名密码组合" ;
    }
}

