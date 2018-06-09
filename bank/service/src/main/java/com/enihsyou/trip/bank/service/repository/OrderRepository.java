package com.enihsyou.trip.bank.service.repository;

import com.enihsyou.trip.bank.service.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
