package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.client.balance.BalanceClientEndpoint;
import com.bilyoner.assignment.couponapi.client.balance.BalanceClientService;
import com.bilyoner.assignment.couponapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.couponapi.model.UserBalanceValidateRequest;
import com.bilyoner.assignment.couponapi.model.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class BalanceClientServiceTest {

    @InjectMocks
    private BalanceClientService balanceClientService;

    @Mock
    private BalanceClientEndpoint balanceClientEndpoint;

    @Test
    public void shouldUpdateBalance() {
        //given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.TEN;
        TransactionType transactionType = TransactionType.REFUND;
        ArgumentCaptor<UpdateBalanceRequest> argumentCaptor = ArgumentCaptor
                .forClass(UpdateBalanceRequest.class);

        balanceClientService.updateBalance(userId, amount, transactionType);
        //verify
        verify(balanceClientEndpoint).updateBalance(argumentCaptor.capture());
        assertEquals(userId, argumentCaptor.getValue().getUserId());
        assertEquals(amount, argumentCaptor.getValue().getAmount());
        assertEquals(transactionType, argumentCaptor.getValue().getTransactionType());
    }

    @Test
    public void shouldValidate() {
        //given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.TEN;
        ArgumentCaptor<UserBalanceValidateRequest> argumentCaptor = ArgumentCaptor
                .forClass(UserBalanceValidateRequest.class);

        balanceClientService.validate(userId, amount);
        //verify
        verify(balanceClientEndpoint).validate(argumentCaptor.capture());
        assertEquals(userId, argumentCaptor.getValue().getUserId());
        assertEquals(amount, argumentCaptor.getValue().getAmount());
    }
}
