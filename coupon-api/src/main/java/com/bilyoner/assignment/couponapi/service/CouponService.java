package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.config.HazelcastCacheConfig;
import com.bilyoner.assignment.couponapi.entity.Coupon;
import com.bilyoner.assignment.couponapi.entity.CouponStatus;
import com.bilyoner.assignment.couponapi.model.CouponCreateRequest;
import com.bilyoner.assignment.couponapi.model.CouponDTO;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final HazelcastInstance hazelcastInstance;

    public List<CouponDTO> getAllCouponByCouponStatus(CouponStatus couponStatus) {
        /**
         * TODO : Implement get coupons logic
         */
        return null;
    }

    public CouponDTO createCoupon(CouponCreateRequest couponCreateRequest) {

        /**
         * TODO : Implement create coupon logic
         */

        Coupon createdCoupon = null;
        CouponDTO response = null;

        // updating in-memory cache
        putCoupon(response.getId(), response);

        return response;
    }

    public CouponDTO putCoupon(Long key, CouponDTO coupon) {
        // evict list
        hazelcastInstance.getMap(HazelcastCacheConfig.COUPONS_BY_STATUS).evictAll();

        IMap<Long, CouponDTO> map = hazelcastInstance.getMap(HazelcastCacheConfig.COUPONS);
        return map.put(key, coupon);
    }

}
