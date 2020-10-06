package com.bilyoner.assignment.couponapi.exception;

import com.bilyoner.assignment.couponapi.exception.BaseRuntimeException;
import com.bilyoner.assignment.couponapi.exception.HttpAwareErrorCode;

public class BalanceApiException extends BaseRuntimeException {

	public BalanceApiException(HttpAwareErrorCode errorCode) {
		super(errorCode);
	}

	public BalanceApiException(HttpAwareErrorCode errorCode, String message) {
		super(errorCode, message);
	}

	public BalanceApiException(HttpAwareErrorCode errorCode, Throwable t) {
		super(errorCode, t);
	}
}
