package com.bilyoner.assignment.balanceapi;

import com.bilyoner.assignment.balanceapi.controller.BalanceController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BalanceApiApplicationTest {

    @Autowired
    private BalanceController balanceController;

    @Test
    public void contextLoadTest() {
        assertThat(balanceController).isNotNull();
    }
}