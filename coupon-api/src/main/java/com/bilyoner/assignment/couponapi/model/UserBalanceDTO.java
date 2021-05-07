package com.bilyoner.assignment.couponapi.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class UserBalanceDTO {

    private Long userId;
    private BigDecimal amount;

}
