package com.bilyoner.assignment.balanceapi.validator;

import com.bilyoner.assignment.balanceapi.exception.BalanceApiException;
import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserBalanceValidatorTest {

    @InjectMocks
    private UserBalanceValidator userBalanceValidator;

    @Test
    public void shouldValidate() {
        //given
        UserBalanceEntity userBalance = UserBalanceEntity.builder()
                .amount(BigDecimal.TEN).build();
        BigDecimal amount = BigDecimal.ONE;

        //verify
        userBalanceValidator.validate(userBalance, amount);
    }

    @Test(expected = BalanceApiException.class)
    public void shouldNotValidate() {
        //given
        UserBalanceEntity userBalance = UserBalanceEntity.builder()
                .amount(BigDecimal.TEN).build();
        BigDecimal amount = BigDecimal.valueOf(100);

        //verify
        userBalanceValidator.validate(userBalance, amount);
    }

}
