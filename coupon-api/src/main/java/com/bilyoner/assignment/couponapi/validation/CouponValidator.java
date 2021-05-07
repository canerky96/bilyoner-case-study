package com.bilyoner.assignment.couponapi.validation;

import com.bilyoner.assignment.couponapi.entity.CouponEntity;
import com.bilyoner.assignment.couponapi.model.UserBalanceDTO;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponValidator {

    private final RestTemplate restTemplate;

    public void validate(List<CouponEntity> coupons, Long userId) {
        validateStatus(coupons);
        validateBudget(coupons, userId);
    }

    private void validateStatus(List<CouponEntity> coupons) {

        boolean isCreatedCoupons = coupons
                .stream()
                .noneMatch(coupon -> CouponStatusEnum.PLAYED.equals(coupon.getStatus()));

        if (!isCreatedCoupons) {
            // TODO throw exception
        }

    }

    private void validateBudget(List<CouponEntity> coupons, Long userId) {

        BigDecimal totalCost = coupons.stream()
                .map(CouponEntity::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal userBalanceAmount = Optional.ofNullable(restTemplate
                .getForObject("http://localhost:9090/balances" + userId, UserBalanceDTO.class))
                .map(UserBalanceDTO::getAmount)
                .orElse(BigDecimal.ZERO);

        if (userBalanceAmount.compareTo(totalCost) < 0) {
            // TODO throw exception
        }

    }

}
