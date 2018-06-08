package com.enihsyou.trip.bank.service.exception;

import com.enihsyou.trip.bank.service.enums.AuthorityType;

public class AuthorityNotGrantedException extends BaseRestException {

    private AuthorityType authority;

    public AuthorityNotGrantedException(AuthorityType authority) {
        this.authority = authority;
    }

    @Override
    public String getMessage() {
        return String.format("未取得所需权限: [%s]", authority);
    }
}
