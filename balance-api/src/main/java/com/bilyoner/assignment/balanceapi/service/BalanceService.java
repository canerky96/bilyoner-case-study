package com.bilyoner.assignment.balanceapi.service;

import com.bilyoner.assignment.balanceapi.exception.BalanceApiException;
import com.bilyoner.assignment.balanceapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.balanceapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.balanceapi.model.UserBalanceDTO;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import com.bilyoner.assignment.balanceapi.persistence.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserBalanceHistoryService userBalanceHistoryService;

    @Transactional(rollbackFor = Exception.class)
    public void updateBalance(UpdateBalanceRequest updateBalanceRequest) {

        UserBalanceEntity userBalance = userBalanceRepository
                .findByUserId(updateBalanceRequest.getUserId())
                .orElseThrow(() -> new BalanceApiException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR));

        if ("WITHDRAW".equalsIgnoreCase(updateBalanceRequest.getTransactionType())) {
            userBalance.setAmount(userBalance.getAmount().subtract(updateBalanceRequest.getAmount()));
        } else if ("REFUND".equalsIgnoreCase(updateBalanceRequest.getTransactionType())) {
            userBalance.setAmount(userBalance.getAmount().add(updateBalanceRequest.getAmount()));
        }

        userBalanceRepository.save(userBalance);
        userBalanceHistoryService.updateHistory(updateBalanceRequest);
    }

    public UserBalanceDTO getByUserId(Long userId) {
        return userBalanceRepository.findByUserId(userId)
                .map(UserBalanceDTO::mapToUserBalanceDTO)
                .orElseThrow(() -> new BalanceApiException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR));
    }
}
