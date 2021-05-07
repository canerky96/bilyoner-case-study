package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.client.balance.BalanceClientService;
import com.bilyoner.assignment.couponapi.model.enums.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceClientService balanceClientService;

    public void updateBalance(Long userId, BigDecimal amount, TransactionType transactionType) {
        balanceClientService.updateBalance(userId, amount, transactionType);
    }

    public void validate(Long userId, BigDecimal amount) {
        balanceClientService.validate(userId, amount);
    }
}
