package com.enihsyou.trip.bank.service.endpoint;

import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("transactions")
public interface BankTransactionEndpoint {

    /**
     * Given the access token obtained during login, this endpoint returns a user's transaction history.
     */
    @GetMapping
    ResponseEntity<AccountDetailVO> accountTransactionDetails();

    /**
     * Start a new transaction on this account.
     * <p>
     * this endpoint returns a transaction id to identify this transaction
     * and a token used in the future commit.
     * <p>
     * Access token is obtained during login.
     */
    @PostMapping
    ResponseEntity startTransaction(String command);

    /**
     * Fetch information of the transaction identified by {@code transactionId}.
     * <p>
     * Information is immutable after transaction is committed.
     *
     * @param transactionId identifier of the transaction.
     */
    @GetMapping("{transactionId}")
    ResponseEntity detailTransaction(@PathVariable("transactionId") String transactionId);
}
