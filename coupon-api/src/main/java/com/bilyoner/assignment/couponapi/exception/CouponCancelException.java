package com.bilyoner.assignment.couponapi.exception;


import com.bilyoner.assignment.couponapi.exception.BaseRuntimeException;
import com.bilyoner.assignment.couponapi.exception.HttpAwareErrorCode;

public class CouponCancelException extends BaseRuntimeException {

	public CouponCancelException(HttpAwareErrorCode errorCode) {
		super(errorCode);
	}
}
