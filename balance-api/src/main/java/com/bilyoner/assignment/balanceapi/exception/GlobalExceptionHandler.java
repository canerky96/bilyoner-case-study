package com.bilyoner.assignment.balanceapi.exception;

import com.bilyoner.assignment.balanceapi.model.ApiBaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BalanceApiException.class)
    private ResponseEntity<ApiBaseResponse> handleServiceExceptions(BalanceApiException exception) {
        //todo exception handler for balance exception
        return null;
    }

    //todo other possible exception handler

    private ResponseEntity<ApiBaseResponse> createResponse() {
        //todo create response
        return null;
    }
}
