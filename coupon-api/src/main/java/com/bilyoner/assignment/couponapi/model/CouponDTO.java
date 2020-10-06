package com.bilyoner.assignment.couponapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import com.bilyoner.assignment.couponapi.entity.Coupon;
import com.bilyoner.assignment.couponapi.entity.CouponStatus;
import com.bilyoner.assignment.couponapi.entity.Event;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CouponDTO implements Serializable {

	private Long id;
	private Long userId;
	private CouponStatus status;
	private Double cost;
	private List<Long> eventIds;
	private LocalDateTime playDate;

	public static CouponDTO mapToCouponDTO(Coupon coupon) {
		return CouponDTO.builder()
				.id(coupon.getId())
				.status(coupon.getStatus())
				.cost(coupon.getCost())
				.userId(coupon.getUserId())
				.eventIds(coupon.getEvents().stream()
						.map(Event::getId)
						.collect(Collectors.toList()))
				.playDate(coupon.getPlayDate())
				.build();
	}
}
