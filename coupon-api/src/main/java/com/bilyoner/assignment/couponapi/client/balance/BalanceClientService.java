package com.bilyoner.assignment.couponapi.client.balance;

import com.bilyoner.assignment.couponapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.couponapi.model.UserBalanceDTO;
import com.bilyoner.assignment.couponapi.model.UserBalanceValidateRequest;
import com.bilyoner.assignment.couponapi.model.enums.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BalanceClientService {

    private final BalanceClientEndpoint balanceClientEndpoint;

    public void updateBalance(Long userId,
                              BigDecimal amount,
                              TransactionType transactionType) {

        UpdateBalanceRequest request = UpdateBalanceRequest.builder()
                .transactionId(UUID.randomUUID().toString())
                .userId(userId)
                .amount(amount)
                .transactionType(transactionType)
                .build();

        balanceClientEndpoint.updateBalance(request);
    }

    public Optional<UserBalanceDTO> validate(Long userId, BigDecimal amount) {
        UserBalanceValidateRequest request = UserBalanceValidateRequest.builder()
                .userId(userId)
                .amount(amount)
                .build();
        return Optional.ofNullable(balanceClientEndpoint.validate(request));
    }

}
