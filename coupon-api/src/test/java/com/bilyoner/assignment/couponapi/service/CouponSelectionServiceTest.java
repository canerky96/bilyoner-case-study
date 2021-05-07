package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.entity.CouponEntity;
import com.bilyoner.assignment.couponapi.entity.CouponSelectionEntity;
import com.bilyoner.assignment.couponapi.entity.EventEntity;
import com.bilyoner.assignment.couponapi.exception.CouponApiException;
import com.bilyoner.assignment.couponapi.model.CouponPlayRequest;
import com.bilyoner.assignment.couponapi.model.enums.CouponStatusEnum;
import com.bilyoner.assignment.couponapi.model.enums.TransactionType;
import com.bilyoner.assignment.couponapi.repository.CouponRepository;
import com.bilyoner.assignment.couponapi.repository.CouponSelectionRepository;
import com.bilyoner.assignment.couponapi.validation.CouponValidator;
import com.bilyoner.assignment.couponapi.validation.EventValidator;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CouponSelectionServiceTest {

    @InjectMocks
    private CouponSelectionService couponSelectionService;
    @Mock
    private CouponSelectionRepository couponSelectionRepository;
    @Mock
    private EventValidator eventValidator;
    @Mock
    private EventService eventService;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private CouponValidator couponValidator;
    @Mock
    private BalanceService balanceService;

    @Test
    public void shouldGetAllByCouponStatus() {
        //given
        CouponStatusEnum couponStatus = CouponStatusEnum.CREATED;

        couponSelectionService.getAllByCouponStatus(couponStatus);
        //verify
        verify(couponSelectionRepository).findAllByCoupon_Status(couponStatus);
    }

    @Test
    public void shouldCreate() {
        //given
        List<Long> eventIds = Collections.singletonList(1L);
        EventEntity eventEntity = EventEntity.builder().build();
        ArgumentCaptor<CouponSelectionEntity> argumentCaptor = ArgumentCaptor
                .forClass(CouponSelectionEntity.class);

        //when
        when(eventService.findByIdIn(anyCollection()))
                .thenReturn(Collections.singletonList(eventEntity));

        couponSelectionService.create(eventIds);

        //verify
        verify(eventService).findByIdIn(eventIds);
        verify(couponSelectionRepository).save(argumentCaptor.capture());

        assertEquals(CouponStatusEnum.CREATED, argumentCaptor.getValue().getCoupon().getStatus());
    }

    @Test
    public void shouldGetPlayedCoupons() {
        //given
        Long userId = 1L;

        couponSelectionService.getPlayedCoupons(userId);

        //verify
        verify(couponSelectionRepository)
                .findAllByCoupon_StatusAndCoupon_UserId(CouponStatusEnum.PLAYED, userId);
    }

    @Test
    public void shouldPlayCoupons() {
        //given
        List<Long> couponIds = Collections.singletonList(1L);
        CouponPlayRequest request = CouponPlayRequest.builder()
                .userId(2L)
                .couponIds(couponIds)
                .build();

        CouponEntity coupon = CouponEntity.builder()
                .cost(BigDecimal.valueOf(5))
                .status(CouponStatusEnum.CREATED).build();
        EventEntity event = EventEntity.builder()
                .name("event").build();

        CouponSelectionEntity couponSelection = CouponSelectionEntity
                .builder().coupon(coupon)
                .events(Collections.singletonList(event)).build();


        //when
        when(couponSelectionRepository.findAllByCoupon_IdIn(anyCollection()))
                .thenReturn(Collections.singletonList(couponSelection));

        couponSelectionService.playCoupons(request);
        //verify
        verify(couponSelectionRepository).findAllByCoupon_IdIn(couponIds);
        verify(balanceService).validate(request.getUserId(), BigDecimal.valueOf(5));
        verify(balanceService).updateBalance(request.getUserId(),
                BigDecimal.valueOf(5),
                TransactionType.WITHDRAW);
    }

    @Test
    public void shouldCancelCoupon() {
        //given
        Long couponId = 1L;
        Long userId = 2L;
        CouponEntity coupon = CouponEntity.builder()
                .cost(BigDecimal.valueOf(5))
                .userId(userId)
                .status(CouponStatusEnum.CREATED).build();
        EventEntity event = EventEntity.builder()
                .name("event").build();

        CouponSelectionEntity couponSelection = CouponSelectionEntity
                .builder().coupon(coupon)
                .events(Collections.singletonList(event)).build();

        //when
        when(couponSelectionRepository.findByCoupon_Id(couponId))
                .thenReturn(Optional.of(couponSelection));

        couponSelectionService.cancelCoupon(couponId);
        //verify
        verify(couponSelectionRepository).findByCoupon_Id(couponId);
        verify(couponValidator).validateForCancel(coupon);
        verify(balanceService).updateBalance(userId, coupon.getCost(), TransactionType.REFUND);
        verify(couponRepository).save(coupon);
    }

    @Test(expected = CouponApiException.class)
    public void shouldNotCancelWhenCouponSelectionNotExists() {
        //given
        Long couponId = 1L;

        //when
        when(couponSelectionRepository.findByCoupon_Id(anyLong()))
                .thenReturn(Optional.empty());

        couponSelectionService.cancelCoupon(couponId);
        //verify
        verify(couponSelectionRepository).findByCoupon_Id(couponId);

    }
}
