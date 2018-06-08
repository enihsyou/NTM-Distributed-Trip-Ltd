package com.enihsyou.trip.bank.service.exception;

import com.enihsyou.trip.bank.service.service.BankService;
import com.fasterxml.jackson.databind.ser.Serializers.Base;

public class UsernameAlreadyExistException extends BaseRestException {

    @Override
    public String getMessage() {
        return "用户名已存在" ;
    }
}

