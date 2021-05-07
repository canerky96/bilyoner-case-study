package com.bilyoner.assignment.balanceapi.validator;

import com.bilyoner.assignment.balanceapi.exception.BalanceApiException;
import com.bilyoner.assignment.balanceapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserBalanceValidator {

    public void validate(UserBalanceEntity userBalance, BigDecimal amount) {
        validateAmount(userBalance, amount);
    }

    private void validateAmount(UserBalanceEntity userBalance, BigDecimal amount) {

        if (userBalance.getAmount().compareTo(amount) < 0) {
            throw new BalanceApiException(ErrorCodeEnum.INSUFFICIENT_BALANCE);
        }

    }
}
