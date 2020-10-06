package com.bilyoner.assignment.couponapi.exception;

import com.bilyoner.assignment.couponapi.exception.BaseRuntimeException;
import com.bilyoner.assignment.couponapi.exception.HttpAwareErrorCode;

public class CouponCreateException extends BaseRuntimeException {

	public CouponCreateException(HttpAwareErrorCode errorCode) {
		super(errorCode);
	}
}
