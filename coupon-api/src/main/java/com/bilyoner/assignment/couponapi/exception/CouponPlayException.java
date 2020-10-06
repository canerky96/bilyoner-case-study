package com.bilyoner.assignment.couponapi.exception;


import com.bilyoner.assignment.couponapi.exception.BaseRuntimeException;
import com.bilyoner.assignment.couponapi.exception.HttpAwareErrorCode;

public class CouponPlayException extends BaseRuntimeException {

	public CouponPlayException(HttpAwareErrorCode errorCode) {
		super(errorCode);
	}
}
