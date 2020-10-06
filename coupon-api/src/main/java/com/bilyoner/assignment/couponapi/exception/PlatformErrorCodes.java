package com.bilyoner.assignment.couponapi.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum PlatformErrorCodes implements HttpAwareErrorCode {

    /*
     * General Error Codes : 1000 to 1050
     */
    INTERNAL_SERVER_ERROR(1001, HttpStatus.OK),
    CONTENT_NOT_FOUND_ERROR(1002, HttpStatus.OK),
    /**
     * Coupon Create Exceptions
     */
    EVENT_ALREADY_STARTED_ERROR(1005, HttpStatus.OK),
    EVENTS_COUNT_MBS_ERROR(1006, HttpStatus.OK),
    EVENTS_INCLUDES_FOOTBALL_AND_TENNIS_ERROR(1007, HttpStatus.OK),
    /**
     * Balanceapi Exceptions
     */
    BALANCEAPI_FAILED_RESPONSE_ERROR(1008, HttpStatus.OK),
    BALANCEAPI_INSUFFICIENT_AMOUNT_ERROR(1009, HttpStatus.OK),
    COUPON_ALREADY_PLAYED_ERROR(1010, HttpStatus.OK),
    INVALID_BALANCEAPI_RESPONSE_ERROR(1011, HttpStatus.OK),
    COUPON_CANCEL_DATE_EXPIRED_ERROR(1012, HttpStatus.OK);

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
        return "COUPON-API";
    }

}
