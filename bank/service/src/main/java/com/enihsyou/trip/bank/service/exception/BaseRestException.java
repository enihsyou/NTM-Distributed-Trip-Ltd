package com.enihsyou.trip.bank.service.exception;

public abstract class BaseRestException extends RuntimeException {

    @Override
    public abstract String getMessage();
}


