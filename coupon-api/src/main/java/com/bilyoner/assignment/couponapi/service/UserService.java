package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.model.CouponDTO;
import com.bilyoner.assignment.couponapi.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CouponService couponService;
    private final CouponRepository couponRepository;

    @Transactional(readOnly = true)
    public List<CouponDTO> getPlayedCoupons(Long userId) {
        return couponRepository.findAllByUserId(userId).stream()
                .map(CouponDTO::mapToCouponDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CouponDTO> playCoupons(Long userId, List<Long> couponIds) {

        /**
         * TODO : Implement play coupons
         */
        List<CouponDTO> coupons = null;

        // update cache
        coupons.stream().forEach(couponDTO -> couponService.putCoupon(couponDTO.getId(), couponDTO));

        return coupons;
    }

    @Transactional
    public CouponDTO cancelCoupon(Long userId, Long couponId) {
        /**
         * TODO : Implement cancel coupon
         */

        CouponDTO couponDTO = null;

        // update cache
        couponService.putCoupon(couponDTO.getId(), couponDTO);

        return couponDTO;
    }
}