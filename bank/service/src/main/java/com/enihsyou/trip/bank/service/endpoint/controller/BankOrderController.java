package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.domain.Order;
import com.enihsyou.trip.bank.service.endpoint.BankOrderEndpoint;
import com.enihsyou.trip.bank.service.endpoint.value.dto.OrderCreateDTO;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import com.enihsyou.trip.bank.service.endpoint.value.vo.OrderVO;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankOrderController implements BankOrderEndpoint {

    private final BankService bankService;

    public BankOrderController(final BankService bankService) {this.bankService = bankService;}

    @Override
    public ResponseEntity accountOrderDetails() {
        Account account = bankService.detailOrder();
        final AccountDetailVO body = AccountDetailVO.parse(account);
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity createOrder(OrderCreateDTO createDTO) {
        Order order = bankService.createOrder(createDTO);
        return ResponseEntity.ok(OrderVO.parse(order));
    }

    @Override
    public ResponseEntity detailOrder(final String orderId) {
        Order order = bankService.detailOrder(Long.parseLong(orderId));
        return ResponseEntity.ok(OrderVO.parse(order));
    }

    @Override
    public ResponseEntity cancelOrder(final String orderId) {
        bankService.cancelOrder(Long.parseLong(orderId));
        return null;
    }

    @Override
    public ResponseEntity confirmOrder(final String orderId) {
        bankService.confirmOrder(Long.parseLong(orderId));
        return null;
    }
}
