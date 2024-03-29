package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.model.CouponCreateRequest;
import com.bilyoner.assignment.couponapi.model.CouponDTO;
import com.bilyoner.assignment.couponapi.model.CouponPlayRequest;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponSelectionService couponSelectionService;

    public List<CouponDTO> getAllCouponsByCouponStatus(CouponStatusEnum couponStatus) {
        return couponSelectionService.getAllByCouponStatus(couponStatus)
                .stream()
                .map(CouponDTO::mapToEventDTO)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public CouponDTO createCoupon(CouponCreateRequest couponCreateRequest) {
        return CouponDTO.mapToEventDTO(couponSelectionService.create(couponCreateRequest.getEventIds()));
    }

    public List<CouponDTO> playCoupons(CouponPlayRequest couponPlayRequest) {
        return couponSelectionService.playCoupons(couponPlayRequest)
                .stream()
                .map(CouponDTO::mapToEventDTO)
                .collect(Collectors.toList());
    }

    public CouponDTO cancelCoupon(Long couponId) {
        return CouponDTO.mapToEventDTO(couponSelectionService.cancelCoupon(couponId));
    }

    public List<CouponDTO> getPlayedCoupons(Long userId) {
        return couponSelectionService.getPlayedCoupons(userId)
                .stream()
                .map(CouponDTO::mapToEventDTO)
                .collect(Collectors.toList());
    }
}
