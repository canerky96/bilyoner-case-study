package com.bilyoner.assignment.balanceapi.exception;

import com.bilyoner.assignment.balanceapi.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BalanceApiException.class})
    protected ResponseEntity<ErrorResponse> handleBalanceApiException(BalanceApiException ex) {
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
