package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.client.balance.BalanceClientService;
import com.bilyoner.assignment.couponapi.model.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceService balanceService;

    @Mock
    private BalanceClientService balanceClientService;

    @Test
    public void updateBalance() {
        //given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.TEN;
        TransactionType transactionType = TransactionType.WITHDRAW;

        balanceService.updateBalance(userId, amount, transactionType);

        //verify
        verify(balanceClientService).updateBalance(userId, amount, transactionType);

    }

    @Test
    public void shouldValidate() {
        //given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.TEN;

        balanceService.validate(userId, amount);

        //verify
        verify(balanceClientService).validate(userId, amount);
    }
}
