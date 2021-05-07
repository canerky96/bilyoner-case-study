package com.bilyoner.assignment.balanceapi.exception;

import com.bilyoner.assignment.balanceapi.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * TODO : Implement all possible exception handlers
     */
    @ExceptionHandler(value = {BalanceApiException.class})
    protected ResponseEntity<ErrorResponse> handleBalanceApiException(BalanceApiException ex) {
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(ex.getErrorCode().getCode())
                        .message(ex.getErrorCode().getMessage())
                        .build());

    }
}
