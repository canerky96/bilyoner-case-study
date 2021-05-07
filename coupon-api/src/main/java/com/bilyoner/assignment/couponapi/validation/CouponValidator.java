package com.bilyoner.assignment.couponapi.validation;

import com.bilyoner.assignment.couponapi.entity.CouponEntity;
import com.bilyoner.assignment.couponapi.exception.CouponApiException;
import com.bilyoner.assignment.couponapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponValidator {

    private final RestTemplate restTemplate;

    public void validate(List<CouponEntity> coupons, Long userId) {
        validateStatus(coupons);
    }

    public void validateForCancel(CouponEntity couponEntity) {
        LocalDateTime fiveMinLater = couponEntity.getPlayDate().plusMinutes(5);
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(fiveMinLater)) {
            throw new CouponApiException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
        }
    }

    private void validateStatus(List<CouponEntity> coupons) {

        boolean isCreatedCoupons = coupons
                .stream()
                .noneMatch(coupon -> CouponStatusEnum.PLAYED.equals(coupon.getStatus()));

        if (!isCreatedCoupons) {
            throw new CouponApiException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
        }

    }


}
