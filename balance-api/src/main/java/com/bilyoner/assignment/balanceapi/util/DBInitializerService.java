package com.bilyoner.assignment.balanceapi.util;

import com.bilyoner.assignment.balanceapi.repository.UserBalanceRepository;
import com.bilyoner.assignment.balanceapi.entity.UserBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBInitializerService {

    private final UserBalanceRepository userBalanceRepository;

    /**
     * Populates sample balance information to user balance table.
     */
    @PostConstruct
    private void initDb() {

        if (userBalanceRepository.count() <= 0) {
            createUserBalances();
        }
    }

    private void createUserBalances() {
        UserBalance userBalance1 = UserBalance.builder()
                .userId(1L)
                .amount(10D)
                .build();

        UserBalance userBalance2 = UserBalance.builder()
                .userId(2L)
                .amount(20D)
                .build();

        UserBalance userBalance3 = UserBalance.builder()
                .userId(3L)
                .amount(30D)
                .build();

        UserBalance userBalance4 = UserBalance.builder()
                .userId(4L)
                .amount(40D)
                .build();

        userBalanceRepository.saveAll(Arrays.asList(userBalance1, userBalance2,
                userBalance3, userBalance4));
    }
}
