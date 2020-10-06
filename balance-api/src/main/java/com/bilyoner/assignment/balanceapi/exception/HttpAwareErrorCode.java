package com.bilyoner.assignment.balanceapi.exception;

import org.springframework.http.HttpStatus;

public interface HttpAwareErrorCode {

	String prefix();
	Integer code();
	HttpStatus httpStatus();
}
