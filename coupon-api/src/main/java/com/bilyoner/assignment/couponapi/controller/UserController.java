package com.bilyoner.assignment.couponapi.controller;

import java.util.List;

import javax.validation.Valid;

import com.bilyoner.assignment.couponapi.model.ApiBaseResponse;
import com.bilyoner.assignment.couponapi.model.CouponPlayRequest;
import com.bilyoner.assignment.couponapi.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilyoner.assignment.couponapi.model.CouponDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/{userId}/coupons")
	public ApiBaseResponse<List<CouponDTO>> getPlayedCoupons(@PathVariable Long userId) {
		return ApiBaseResponse.<List<CouponDTO>>builder()
				.operationResultData(userService.getPlayedCoupons(userId))
				.build();
	}

	@PostMapping("/{userId}/coupons")
	public ApiBaseResponse<List<CouponDTO>> playCoupons(@PathVariable Long userId, @Valid @RequestBody CouponPlayRequest couponPlayRequest) {
		return ApiBaseResponse.<List<CouponDTO>>builder()
				.operationResultData(userService.playCoupons(userId, couponPlayRequest.getCouponIds()))
				.build();
	}

	@DeleteMapping("/{userId}/coupons/{couponId}")
	public ApiBaseResponse<CouponDTO> cancelCoupon(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "couponId") Long couponId) {
		return ApiBaseResponse.<CouponDTO>builder()
				.operationResultData(userService.cancelCoupon(userId, couponId))
				.build();
	}

}
