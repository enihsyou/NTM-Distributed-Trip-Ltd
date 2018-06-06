package com.enihsyou.trip.bank.service.service;

import com.enihsyou.trip.bank.service.domain.Transaction;

public interface BankService {

    Transaction startTransaction();

    Transaction modifyTransaction();

    Transaction commitTransaction();


}

