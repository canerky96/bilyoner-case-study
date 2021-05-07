package com.bilyoner.assignment.couponapi.client.balance;

import com.bilyoner.assignment.couponapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.couponapi.model.UserBalanceDTO;
import com.bilyoner.assignment.couponapi.model.UserBalanceValidateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "balanceClient", url = "http://localhost:9090/balances/")
public interface BalanceClientEndpoint {

    @PutMapping
    void updateBalance(@Valid @RequestBody UpdateBalanceRequest updateBalanceRequest);

    @PostMapping("validate")
    ResponseEntity<UserBalanceDTO> validate(@Validated @RequestBody UserBalanceValidateRequest validateRequest);

}
