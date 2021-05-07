package com.bilyoner.assignment.balanceapi.service;

import com.bilyoner.assignment.balanceapi.exception.BalanceApiException;
import com.bilyoner.assignment.balanceapi.model.UpdateBalanceRequest;
import com.bilyoner.assignment.balanceapi.model.UserBalanceValidateRequest;
import com.bilyoner.assignment.balanceapi.model.enums.TransactionType;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import com.bilyoner.assignment.balanceapi.persistence.repository.UserBalanceRepository;
import com.bilyoner.assignment.balanceapi.validator.UserBalanceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceService balanceService;

    @Mock
    private UserBalanceRepository userBalanceRepository;

    @Mock
    private UserBalanceHistoryService userBalanceHistoryService;

    @Mock
    private UserBalanceValidator userBalanceValidator;

    private UserBalanceEntity userBalance;

    @Before
    public void init() {
        Long userId = 1L;
        userBalance = UserBalanceEntity.builder()
                .userId(userId)
                .amount(BigDecimal.valueOf(10))
                .build();
    }

    @Test
    public void shouldWithdrawBalance() {
        //given
        BigDecimal updatedAmount = BigDecimal.valueOf(5);
        UpdateBalanceRequest request = UpdateBalanceRequest.builder()
                .userId(userBalance.getUserId())
                .amount(updatedAmount)
                .transactionType(TransactionType.WITHDRAW)
                .build();

        ArgumentCaptor<UserBalanceEntity> argumentCaptor = ArgumentCaptor.forClass(UserBalanceEntity.class);
        //when
        when(userBalanceRepository.findByUserId(anyLong()))
                .thenReturn(Optional.of(userBalance));

        balanceService.updateBalance(request);
        //verify
        verify(userBalanceValidator).validate(userBalance, updatedAmount);
        verify(userBalanceRepository).save(argumentCaptor.capture());
        verify(userBalanceHistoryService).updateHistory(request);

        // 10-5 = 5
        assertEquals(BigDecimal.valueOf(5), argumentCaptor.getValue().getAmount());
    }

    @Test
    public void shouldRefundBalance() {
        //given
        BigDecimal updatedAmount = BigDecimal.valueOf(5);
        UpdateBalanceRequest request = UpdateBalanceRequest.builder()
                .userId(userBalance.getUserId())
                .amount(updatedAmount)
                .transactionType(TransactionType.REFUND)
                .build();
        ArgumentCaptor<UserBalanceEntity> argumentCaptor = ArgumentCaptor.forClass(UserBalanceEntity.class);
        //when
        when(userBalanceRepository.findByUserId(anyLong()))
                .thenReturn(Optional.of(userBalance));

        balanceService.updateBalance(request);

        //verify
        verify(userBalanceRepository).save(argumentCaptor.capture());
        verify(userBalanceHistoryService).updateHistory(request);
        // 10+5 = 15
        assertEquals(BigDecimal.valueOf(15), argumentCaptor.getValue().getAmount());
    }

    @Test(expected = BalanceApiException.class)
    public void shouldNotUpdateWhenUserBalanceNotExists() {
        //given
        BigDecimal updatedAmount = BigDecimal.valueOf(5);
        UpdateBalanceRequest request = UpdateBalanceRequest.builder()
                .userId(userBalance.getUserId())
                .amount(updatedAmount)
                .transactionType(TransactionType.REFUND)
                .build();
        //when
        when(userBalanceRepository.findByUserId(anyLong()))
                .thenReturn(Optional.empty());

        balanceService.updateBalance(request);

    }

    @Test
    public void shouldValidate() {
        //given
        BigDecimal validatedAmount = BigDecimal.valueOf(5);
        UserBalanceValidateRequest request = UserBalanceValidateRequest
                .builder()
                .userId(userBalance.getUserId())
                .amount(validatedAmount)
                .build();

        //when
        when(userBalanceRepository.findByUserId(anyLong()))
                .thenReturn(Optional.of(userBalance));

        balanceService.validate(request);

        //verify
        verify(userBalanceValidator).validate(userBalance, validatedAmount);
    }

    @Test(expected = BalanceApiException.class)
    public void shouldNotValidateWhenUserBalanceNotExists() {
        //given
        BigDecimal validatedAmount = BigDecimal.valueOf(5);
        UserBalanceValidateRequest request = UserBalanceValidateRequest
                .builder()
                .userId(userBalance.getUserId())
                .amount(validatedAmount)
                .build();
        //when
        when(userBalanceRepository.findByUserId(anyLong()))
                .thenReturn(Optional.empty());

        balanceService.validate(request);
    }
}
