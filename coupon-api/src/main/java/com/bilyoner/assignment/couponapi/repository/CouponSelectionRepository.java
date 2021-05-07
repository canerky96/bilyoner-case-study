package com.bilyoner.assignment.couponapi.repository;

import com.bilyoner.assignment.couponapi.entity.CouponSelectionEntity;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CouponSelectionRepository extends JpaRepository<CouponSelectionEntity, Long> {

    List<CouponSelectionEntity> findAllByCoupon_Status(CouponStatusEnum status);

    List<CouponSelectionEntity> findAllByCoupon_StatusAndCoupon_UserId(CouponStatusEnum statusEnum, Long userId);

    List<CouponSelectionEntity> findAllByCoupon_IdIn(Collection<Long> couponIds);

    Optional<CouponSelectionEntity> findByCoupon_Id(Long couponId);
}
