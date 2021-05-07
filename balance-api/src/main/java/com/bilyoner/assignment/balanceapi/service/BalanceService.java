package com.bilyoner.assignment.balanceapi.service;

import com.bilyoner.assignment.balanceapi.exception.BalanceApiException;
import com.bilyoner.assignment.balanceapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.balanceapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.balanceapi.model.UserBalanceDTO;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import com.bilyoner.assignment.balanceapi.persistence.repository.UserBalanceRepository;
import com.bilyoner.assignment.balanceapi.validator.UserBalanceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserBalanceHistoryService userBalanceHistoryService;
    private final UserBalanceValidator userBalanceValidator;

    @Transactional(rollbackFor = Exception.class)
    public void updateBalance(UpdateBalanceRequest updateBalanceRequest) {

        UpdateBalanceRequest.validate(updateBalanceRequest);

        UserBalanceEntity userBalance = userBalanceRepository
                .findByUserId(updateBalanceRequest.getUserId())
                .orElseThrow(() -> new BalanceApiException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR));
        userBalanceValidator.validate(userBalance);

        updateBalance(userBalance, updateBalanceRequest);
        userBalanceHistoryService.updateHistory(updateBalanceRequest);
    }

    public UserBalanceDTO getByUserId(Long userId) {
        return userBalanceRepository.findByUserId(userId)
                .map(UserBalanceDTO::mapToUserBalanceDTO)
                .orElseThrow(() -> new BalanceApiException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR));
    }

    private void updateBalance(UserBalanceEntity userBalance, UpdateBalanceRequest updateBalanceRequest) {

        if ("WITHDRAW".equalsIgnoreCase(updateBalanceRequest.getTransactionType())) {
            userBalance.setAmount(userBalance.getAmount().subtract(updateBalanceRequest.getAmount()));
        } else if ("REFUND".equalsIgnoreCase(updateBalanceRequest.getTransactionType())) {
            userBalance.setAmount(userBalance.getAmount().add(updateBalanceRequest.getAmount()));
        }

        if (updateBalanceRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceApiException(ErrorCodeEnum.INSUFFICIENT_BALANCE);
        }

        userBalanceRepository.save(userBalance);
    }

}
