package com.bilyoner.assignment.balanceapi.exception;

public class BalanceApiException extends BaseRuntimeException{

	public BalanceApiException(HttpAwareErrorCode errorCode) {
		super(errorCode);
	}
}
