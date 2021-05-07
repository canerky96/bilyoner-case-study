package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.entity.CouponEntity;
import com.bilyoner.assignment.couponapi.entity.CouponSelectionEntity;
import com.bilyoner.assignment.couponapi.entity.EventEntity;
import com.bilyoner.assignment.couponapi.model.CouponCreateRequest;
import com.bilyoner.assignment.couponapi.model.CouponPlayRequest;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CouponServiceTest {

    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponSelectionService couponSelectionService;

    @Test
    public void shouldGetAllCouponsByCouponStatus() {
        //given
        CouponStatusEnum couponStatus = CouponStatusEnum.CREATED;

        couponService.getAllCouponsByCouponStatus(couponStatus);
        //verify
        verify(couponSelectionService).getAllByCouponStatus(couponStatus);

    }

    @Test
    public void shouldCreateCoupon() {
        //given
        CouponEntity couponEntity = CouponEntity.builder().build();
        EventEntity eventEntity = EventEntity.builder().build();
        CouponSelectionEntity couponSelectionEntity = CouponSelectionEntity.builder()
                .coupon(couponEntity)
                .events(Collections.singletonList(eventEntity))
                .build();
        Long eventId = 1L;

        //when
        when(couponSelectionService.create(anyCollection())).thenReturn(couponSelectionEntity);

        couponSelectionService.create(Collections.singletonList(eventId));

        //verify
        verify(couponSelectionService).create(Collections.singletonList(eventId));
    }

    @Test
    public void shouldPlayCoupons() {
        //given
        CouponPlayRequest couponPlayRequest = CouponPlayRequest.builder()
                .userId(1L).couponIds(Arrays.asList(1L, 2L)).build();

        couponService.playCoupons(couponPlayRequest);

        verify(couponSelectionService).playCoupons(couponPlayRequest);
    }

    @Test
    public void shouldCancelCoupon() {
        //given
        Long couponId = 2L;
        CouponEntity couponEntity = CouponEntity.builder().build();
        EventEntity eventEntity = EventEntity.builder().build();
        CouponSelectionEntity couponSelectionEntity = CouponSelectionEntity.builder()
                .coupon(couponEntity)
                .events(Collections.singletonList(eventEntity))
                .build();
        //when
        when(couponSelectionService.cancelCoupon(couponId)).thenReturn(couponSelectionEntity);

        couponService.cancelCoupon(couponId);

        //verify
        verify(couponSelectionService).cancelCoupon(couponId);
    }

    @Test
    public void shouldGetPlayedCoupons() {
        //given
        Long userId = 1L;

        couponService.getPlayedCoupons(userId);

        verify(couponSelectionService).getPlayedCoupons(userId);
    }
}
