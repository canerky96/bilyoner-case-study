package com.bilyoner.assignment.balanceapi.exception;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlatformErrorCodes implements HttpAwareErrorCode {

	/*
	 * General Error Codes : 1000 to 1050
	 */
	INTERNAL_SERVER_ERROR(1001, HttpStatus.OK),
	FIELD_VALIDATION_ERROR(1002, HttpStatus.BAD_REQUEST),
	CONTENT_NOT_FOUND_ERROR(1003, HttpStatus.OK);

	private final Integer code;
	private final HttpStatus httpStatus;

	@Override
	public Integer code() {
		return code;
	}

	@Override
	public HttpStatus httpStatus() {
		return httpStatus;
	}

	@Override
	public String prefix() {
		return "BALANCE-API";
	}

}
