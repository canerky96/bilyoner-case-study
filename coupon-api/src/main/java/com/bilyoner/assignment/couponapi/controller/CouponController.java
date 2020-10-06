package com.bilyoner.assignment.couponapi.controller;

import java.util.List;

import javax.validation.Valid;

import com.bilyoner.assignment.couponapi.entity.CouponStatus;
import com.bilyoner.assignment.couponapi.model.ApiBaseResponse;
import com.bilyoner.assignment.couponapi.model.CouponCreateRequest;
import com.bilyoner.assignment.couponapi.model.CouponDTO;
import com.bilyoner.assignment.couponapi.service.CouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

	private final CouponService couponService;

	@GetMapping
	public ApiBaseResponse<List<CouponDTO>> getAllCoupons(@RequestParam(name ="couponStatus") CouponStatus couponStatus) {
		return ApiBaseResponse.<List<CouponDTO>>builder()
				.operationResultData(couponService.getAllCouponByCouponStatus(couponStatus))
				.build();
	}

	@PostMapping
	public ApiBaseResponse<CouponDTO> createCoupon(@RequestBody @Valid CouponCreateRequest couponCreateRequest) {
		return ApiBaseResponse.<CouponDTO>builder()
				.operationResultData(couponService.createCoupon(couponCreateRequest))
				.build();
	}

}