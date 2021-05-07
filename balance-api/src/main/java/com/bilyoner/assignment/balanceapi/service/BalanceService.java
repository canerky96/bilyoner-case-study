package com.bilyoner.assignment.balanceapi.service;

import com.bilyoner.assignment.balanceapi.exception.BalanceApiException;
import com.bilyoner.assignment.balanceapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.balanceapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.balanceapi.model.UserBalanceDTO;
import com.bilyoner.assignment.balanceapi.model.UserBalanceValidateRequest;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import com.bilyoner.assignment.balanceapi.persistence.repository.UserBalanceRepository;
import com.bilyoner.assignment.balanceapi.validator.UserBalanceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserBalanceHistoryService userBalanceHistoryService;
    private final UserBalanceValidator userBalanceValidator;

    @Transactional(rollbackFor = Exception.class)
    public void updateBalance(UpdateBalanceRequest updateBalanceRequest) {

        UserBalanceEntity userBalance = getByUserId(updateBalanceRequest.getUserId());

        userBalanceValidator.validate(userBalance, updateBalanceRequest.getAmount());
        updateBalance(userBalance, updateBalanceRequest);

        userBalanceHistoryService.updateHistory(updateBalanceRequest);
    }

    public UserBalanceDTO validate(UserBalanceValidateRequest validateRequest) {
        UserBalanceEntity userBalance = getByUserId(validateRequest.getUserId());
        userBalanceValidator.validate(userBalance, validateRequest.getAmount());
        return UserBalanceDTO.mapToUserBalanceDTO(userBalance);
    }

    private UserBalanceEntity getByUserId(Long userId) {
        return userBalanceRepository.findByUserId(userId)
                .orElseThrow(() -> new BalanceApiException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR));
    }

    private void updateBalance(UserBalanceEntity userBalance, UpdateBalanceRequest updateBalanceRequest) {

        if ("WITHDRAW".equalsIgnoreCase(updateBalanceRequest.getTransactionType())) {
            userBalance.setAmount(userBalance.getAmount().subtract(updateBalanceRequest.getAmount()));
        } else if ("REFUND".equalsIgnoreCase(updateBalanceRequest.getTransactionType())) {
            userBalance.setAmount(userBalance.getAmount().add(updateBalanceRequest.getAmount()));
        }

        userBalanceRepository.save(userBalance);
    }
}
