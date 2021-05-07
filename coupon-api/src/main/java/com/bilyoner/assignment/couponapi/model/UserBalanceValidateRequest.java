package com.bilyoner.assignment.couponapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBalanceValidateRequest {

    @NotNull
    private Long userId;
    @NotNull
    private BigDecimal amount;

}