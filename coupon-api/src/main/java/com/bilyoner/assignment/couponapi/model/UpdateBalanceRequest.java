package com.bilyoner.assignment.couponapi.model;

import com.bilyoner.assignment.couponapi.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBalanceRequest {
    @NotNull
    private Long userId;
    @NotNull
    private BigDecimal amount;
    @NotBlank
    private String transactionId;
    @NotBlank
    private TransactionType transactionType;
}
