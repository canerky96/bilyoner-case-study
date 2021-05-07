package com.bilyoner.assignment.couponapi.model;

import com.bilyoner.assignment.couponapi.entity.CouponSelectionEntity;
import com.bilyoner.assignment.couponapi.entity.EventEntity;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CouponDTO implements Serializable {

    private Long id;
    private Long userId;
    private CouponStatusEnum status;
    private BigDecimal cost;
    private List<Long> eventIds;
    private LocalDateTime playDate;

    public static CouponDTO mapToEventDTO(CouponSelectionEntity couponSelectionEntity){
        return CouponDTO.builder()
                .id(couponSelectionEntity.getCoupon().getId())
                .userId(couponSelectionEntity.getCoupon().getUserId())
                .status(couponSelectionEntity.getCoupon().getStatus())
                .cost(couponSelectionEntity.getCoupon().getCost())
                .eventIds(couponSelectionEntity.getEvents()
                        .stream()
                        .map(EventEntity::getId)
                        .collect(Collectors.toList()))
                .playDate(couponSelectionEntity.getCoupon().getPlayDate())
                .build();
    }
}
