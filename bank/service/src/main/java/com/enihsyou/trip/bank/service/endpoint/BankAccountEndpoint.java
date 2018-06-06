package com.enihsyou.trip.bank.service.endpoint;

import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountDetailVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface BankAccountEndpoint {

    /**
     * Given the access token obtained during login, this endpoint returns a user's profile.
     * <p>
     * User's transactions history is also included.
     *
     * @return
     */
    @GetMapping("user_info")
    ResponseEntity<AccountDetailVO> accountDetail();

}

