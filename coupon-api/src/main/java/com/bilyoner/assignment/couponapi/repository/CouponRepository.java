package com.bilyoner.assignment.couponapi.repository;


import com.bilyoner.assignment.couponapi.entity.Coupon;
import com.bilyoner.assignment.couponapi.entity.CouponStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByIdAndUserId(Long id, Long userId);

    List<Coupon> findAllByUserId(Long userId);

    List<Coupon> getAllByStatus(CouponStatus couponStatus);
}
