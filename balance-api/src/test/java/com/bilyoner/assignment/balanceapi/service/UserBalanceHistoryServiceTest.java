package com.bilyoner.assignment.balanceapi.service;

import com.bilyoner.assignment.balanceapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.balanceapi.model.enums.TransactionType;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceHistoryEntity;
import com.bilyoner.assignment.balanceapi.persistence.repository.UserBalanceHistoryRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserBalanceHistoryServiceTest {

    @InjectMocks
    private UserBalanceHistoryService userBalanceHistoryService;

    @Mock
    private UserBalanceHistoryRepository userBalanceHistoryRepository;

    @Test
    public void shouldUpdateHistory() {
        //given
        UpdateBalanceRequest request = UpdateBalanceRequest.builder()
                .userId(1L)
                .amount(BigDecimal.TEN)
                .transactionId("transactionId")
                .transactionType(TransactionType.REFUND)
                .build();
        ArgumentCaptor<UserBalanceHistoryEntity> argumentCaptor = ArgumentCaptor
                .forClass(UserBalanceHistoryEntity.class);

        userBalanceHistoryService.updateHistory(request);

        //verify
        verify(userBalanceHistoryRepository).save(argumentCaptor.capture());
        assertEquals(request.getUserId(), argumentCaptor.getValue().getUserId());
        assertEquals(request.getAmount(), argumentCaptor.getValue().getAmount());
        assertEquals(request.getTransactionId(), argumentCaptor.getValue().getTransactionId());
        assertEquals(request.getTransactionType(), argumentCaptor.getValue().getTransactionType());
    }

}
