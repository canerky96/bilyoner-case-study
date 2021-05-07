package com.bilyoner.assignment.balanceapi.service;

import com.bilyoner.assignment.balanceapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceHistoryEntity;
import com.bilyoner.assignment.balanceapi.persistence.repository.UserBalanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBalanceHistoryService {

    private final UserBalanceHistoryRepository userBalanceHistoryRepository;

    public void updateHistory(UpdateBalanceRequest updateBalanceRequest) {
        UserBalanceHistoryEntity userBalanceHistory = UserBalanceHistoryEntity
                .builder()
                .userId(updateBalanceRequest.getUserId())
                .amount(updateBalanceRequest.getAmount())
                .transactionId(updateBalanceRequest.getTransactionId())
                .transactionType(updateBalanceRequest.getTransactionType())
                .build();
        userBalanceHistoryRepository.save(userBalanceHistory);
    }
}
