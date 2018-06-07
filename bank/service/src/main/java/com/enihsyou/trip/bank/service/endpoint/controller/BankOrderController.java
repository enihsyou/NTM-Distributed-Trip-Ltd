package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.endpoint.BankOrderEndpoint;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankOrderController implements BankOrderEndpoint {

    private final BankService bankService;

    public BankOrderController(final BankService bankService) {this.bankService = bankService;}

    @Override
    public ResponseEntity accountOrderDetails() {
        return null;
    }

    @Override
    public ResponseEntity createOrder() {
        return null;
    }

    @Override
    public ResponseEntity detailOrder(final String orderId) {
        return null;
    }

    @Override
    public ResponseEntity cancelOrder(final String orderId) {
        return null;
    }

    @Override
    public ResponseEntity confirmOrder(final String orderId) {
        return null;
    }
}
