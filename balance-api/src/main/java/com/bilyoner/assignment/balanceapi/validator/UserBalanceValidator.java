package com.bilyoner.assignment.balanceapi.validator;

import com.bilyoner.assignment.balanceapi.exception.BalanceApiException;
import com.bilyoner.assignment.balanceapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserBalanceValidator {

    public void validate(UserBalanceEntity userBalance) {
        validateAmount(userBalance);
    }

    private void validateAmount(UserBalanceEntity userBalance) {

        if (userBalance.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceApiException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
        }

    }
}
