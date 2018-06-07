package com.enihsyou.trip.bank.service.repository;

import com.enihsyou.trip.bank.service.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByUsername(String username);
}
