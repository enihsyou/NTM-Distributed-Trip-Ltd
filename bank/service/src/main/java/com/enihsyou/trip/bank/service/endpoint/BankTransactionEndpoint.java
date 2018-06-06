package com.enihsyou.trip.bank.service.endpoint;

import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface BankTransactionEndpoint {

    /**
     * Given the access token obtained during login, this endpoint returns a user's transaction history.
     *
     * @return
     */
    @GetMapping("transactions")
    ResponseEntity<AccountDetailVO> accountTransactionDetails();

    /**
     * Start a new transaction on this account.
     * <p>
     * this endpoint returns a transaction id to identify this transaction
     * and a token used in the future commit.
     * <p>
     * Access token is obtained during login.
     *
     * @see #commitTransaction(String)
     */
    @PostMapping("transactions")
    ResponseEntity startNewTransaction();

    /**
     * Fetch information of the transaction identified by {@code transactionId}.
     * <p>
     * Information is immutable after transaction is committed.
     *
     * @param transactionId identifier of the transaction.
     *
     * @return
     */
    @GetMapping("transaction/{transactionId}")
    ResponseEntity detailTransaction(@PathVariable("transactionId") String transactionId);

    /**
     * Modify transaction information before commit.
     *
     * @param transactionId identifier of the transaction.
     *
     * @return
     */
    @PutMapping("transaction/{transactionId}")
    ResponseEntity modifyTransaction(@PathVariable("transactionId") String transactionId);

    /**
     * Given the transaction id obtained from {@link #startNewTransaction()}, commit this transaction.
     * <p>
     * Each transaction is immutable after committed, but is mutable before this action.
     *
     * @param transactionId identifier of the transaction to be committed.
     *
     * @return
     */
    @PutMapping("transactions/{transactionId}/commit")
    ResponseEntity commitTransaction(@PathVariable("transactionId") String transactionId);
}
