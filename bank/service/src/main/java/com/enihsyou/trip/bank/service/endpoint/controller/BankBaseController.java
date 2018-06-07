package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.domain.Account;
import com.enihsyou.trip.bank.service.endpoint.BankBaseEndpoint;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountAuthorizeDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountChangePasswordDTO;
import com.enihsyou.trip.bank.service.endpoint.value.dto.AccountSignupDTO;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountAuthorizeVO;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountChangePasswordVO;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountExistenceVO;
import com.enihsyou.trip.bank.service.endpoint.value.vo.AccountSignupVO;
import com.enihsyou.trip.bank.service.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankBaseController implements BankBaseEndpoint {

    private final BankService bankService;

    public BankBaseController(final BankService bankService) {this.bankService = bankService;}

    @Override
    public ResponseEntity accountExistence(final String testUsername) {
        boolean existence = bankService.accountExistence(testUsername);
        final AccountExistenceVO vo = AccountExistenceVO.builder()
            .username(testUsername)
            .existence(existence).build();
        return ResponseEntity.ok(vo);
    }

    @Override
    public ResponseEntity signupAccount(final AccountSignupDTO signupDTO) {
        Account account = bankService.signupAccount(signupDTO);
        final AccountSignupVO vo = AccountSignupVO.builder().build();
        return ResponseEntity.ok(vo);
    }

    @Override
    public ResponseEntity authorizeAccount(final AccountAuthorizeDTO authorizeDTO) {
        Account account = bankService.authorizeAccount(authorizeDTO);
        final AccountAuthorizeVO vo = AccountAuthorizeVO.builder().build();
        return ResponseEntity.ok(vo);
    }

    @Override
    public ResponseEntity changeAccountPassword(final AccountChangePasswordDTO changePasswordDTO) {
        Account account = bankService.changeAccountPassword(changePasswordDTO);
        final AccountChangePasswordVO vo  = AccountChangePasswordVO.builder().build();
        return ResponseEntity.ok(vo);
    }
}
