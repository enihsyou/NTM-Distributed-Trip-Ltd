package com.enihsyou.trip.bank.service.repository;

import com.enihsyou.trip.bank.service.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByUserId(String userId);

    boolean existsByUsername(String username);
}

