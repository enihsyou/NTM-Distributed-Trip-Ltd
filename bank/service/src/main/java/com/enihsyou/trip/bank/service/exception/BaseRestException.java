package com.enihsyou.trip.bank.service.exception;

public abstract class BaseRestException extends RuntimeException {

    protected BaseRestException() {
    }

    protected BaseRestException(String message) {
        super(message);
    }

    @Override
    public abstract String getMessage();
}


