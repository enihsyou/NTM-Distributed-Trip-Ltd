package com.enihsyou.trip.bank.service.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("order")
public interface BankOrderEndpoint {

    /**
     * List user's order list.
     */
    @GetMapping
    ResponseEntity accountOrderDetails();

    /**
     * Client use this endpoint to create an order.
     */
    @PostMapping
    ResponseEntity createOrder();

    @GetMapping("{orderId}")
    ResponseEntity detailOrder(@PathVariable("orderId") String orderId);

    @DeleteMapping("{orderId}")
    ResponseEntity cancelOrder(@PathVariable("orderId") String orderId);

    /**
     * User has acknowledged this order, and confirm to pay this order.
     *
     * @param orderId identifier of the order.
     */
    @PutMapping("{orderId}")
    ResponseEntity confirmOrder(@PathVariable("orderId") String orderId);

}
