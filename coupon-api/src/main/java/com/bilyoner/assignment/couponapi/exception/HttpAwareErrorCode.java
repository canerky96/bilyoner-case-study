package com.bilyoner.assignment.couponapi.exception;

import org.springframework.http.HttpStatus;

public interface HttpAwareErrorCode {

	String prefix();
	Integer code();
	HttpStatus httpStatus();
}
