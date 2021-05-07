package com.bilyoner.assignment.balanceapi.model;

import com.bilyoner.assignment.balanceapi.persistence.entity.UserBalanceEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class UserBalanceDTO {

    private Long userId;
    private BigDecimal amount;

    public static UserBalanceDTO mapToUserBalanceDTO(UserBalanceEntity userBalance) {
        return UserBalanceDTO.builder()
                .userId(userBalance.getUserId())
                .amount(userBalance.getAmount())
                .build();
    }
}
