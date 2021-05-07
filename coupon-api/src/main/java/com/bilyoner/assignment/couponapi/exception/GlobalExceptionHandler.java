package com.bilyoner.assignment.couponapi.exception;

import com.bilyoner.assignment.couponapi.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CouponApiException.class})
    protected ResponseEntity<ErrorResponse> handleBalanceApiException(CouponApiException ex) {
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(ex.getErrorCode().getCode())
                        .message(ex.getErrorCode().getMessage())
                        .build());

    }

    @ExceptionHandler(value = {NullPointerException.class,
            RuntimeException.class,
            StackOverflowError.class,
            NoSuchFieldException.class})
    protected ResponseEntity<ErrorResponse> handleCommonExceptions(NullPointerException ex) {
        return ResponseEntity
                .status(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode())
                        .message(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity
                .status(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode())
                        .message(ex.getMessage())
                        .build());
    }
}