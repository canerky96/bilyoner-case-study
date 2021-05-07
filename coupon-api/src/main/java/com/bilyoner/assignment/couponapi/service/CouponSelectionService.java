package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.entity.CouponEntity;
import com.bilyoner.assignment.couponapi.entity.CouponSelectionEntity;
import com.bilyoner.assignment.couponapi.entity.EventEntity;
import com.bilyoner.assignment.couponapi.exception.CouponApiException;
import com.bilyoner.assignment.couponapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.couponapi.model.CouponPlayRequest;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import com.bilyoner.assignment.couponapi.model.enums.TransactionType;
import com.bilyoner.assignment.couponapi.repository.CouponRepository;
import com.bilyoner.assignment.couponapi.repository.CouponSelectionRepository;
import com.bilyoner.assignment.couponapi.validation.CouponValidator;
import com.bilyoner.assignment.couponapi.validation.EventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponSelectionService {

    private static final int COUPON_COST = 5;

    private final CouponSelectionRepository couponSelectionRepository;
    private final EventValidator eventValidator;
    private final EventService eventService;
    private final CouponRepository couponRepository;
    private final CouponValidator couponValidator;
    private final BalanceService balanceService;

    public List<CouponSelectionEntity> getAllByCouponStatus(CouponStatusEnum couponStatus) {
        return couponSelectionRepository.findAllByCoupon_Status(couponStatus);
    }

    public CouponSelectionEntity create(Collection<Long> eventIds) {
        CouponSelectionEntity couponSelection = new CouponSelectionEntity();

        List<EventEntity> events = eventService.findByIdIn(eventIds);
        eventValidator.validate(events);

        CouponEntity coupon = CouponEntity.builder()
                .status(CouponStatusEnum.CREATED)
                .cost(BigDecimal.valueOf(COUPON_COST))
                .build();

        couponSelection.setEvents(events);
        couponSelection.setCoupon(coupon);

        return couponSelectionRepository.save(couponSelection);
    }

    public List<CouponSelectionEntity> getPlayedCoupons(Long userId) {
        return couponSelectionRepository
                .findAllByCoupon_StatusAndCoupon_UserId(CouponStatusEnum.PLAYED, userId);
    }

    public List<CouponSelectionEntity> playCoupons(CouponPlayRequest couponPlayRequest) {

        List<CouponEntity> updatedCoupons = new ArrayList<>();

        List<CouponSelectionEntity> couponSelections = couponSelectionRepository
                .findAllByCoupon_IdIn(couponPlayRequest.getCouponIds());

        couponValidator.validate(couponSelections.stream()
                        .map(CouponSelectionEntity::getCoupon)
                        .collect(Collectors.toList()),
                couponPlayRequest.getUserId());

        LocalDateTime now = LocalDateTime.now();
        for (CouponSelectionEntity couponSelection : couponSelections) {
            couponSelection.getCoupon().setStatus(CouponStatusEnum.PLAYED);
            couponSelection.getCoupon().setUserId(couponPlayRequest.getUserId());
            couponSelection.getCoupon().setPlayDate(now);
            updatedCoupons.add(couponSelection.getCoupon());
        }

        balanceService.validate(couponPlayRequest.getUserId(), getTotalCost(updatedCoupons));
        balanceService.updateBalance(couponPlayRequest.getUserId(), getTotalCost(updatedCoupons), TransactionType.WITHDRAW);

        couponRepository.saveAll(updatedCoupons);
        return couponSelections;
    }

    public CouponSelectionEntity cancelCoupon(Long couponId) {

        CouponSelectionEntity couponSelection = couponSelectionRepository
                .findByCoupon_Id(couponId)
                .orElseThrow(() -> new CouponApiException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR));
        couponValidator.validateForCancel(couponSelection.getCoupon());

        balanceService.updateBalance(couponSelection.getCoupon().getUserId(),
                couponSelection.getCoupon().getCost(),
                TransactionType.REFUND);

        cancelCoupon(couponSelection.getCoupon());

        return couponSelection;
    }

    private BigDecimal getTotalCost(Collection<CouponEntity> coupons) {
        return coupons.stream().map(CouponEntity::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void cancelCoupon(CouponEntity coupon) {
        coupon.setUserId(null);
        coupon.setStatus(CouponStatusEnum.CREATED);
        coupon.setPlayDate(null);
        couponRepository.save(coupon);
    }

}
