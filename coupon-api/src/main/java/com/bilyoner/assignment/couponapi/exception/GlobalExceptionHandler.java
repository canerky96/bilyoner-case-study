package com.bilyoner.assignment.couponapi.exception;

import java.util.Locale;

import com.bilyoner.assignment.couponapi.config.MessageTextResolver;
import com.bilyoner.assignment.couponapi.model.ApiBaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final MessageTextResolver messageTextResolver;

	@ExceptionHandler(BaseRuntimeException.class)
	ResponseEntity<ApiBaseResponse> handleServiceExceptions(BaseRuntimeException exception, Locale locale) {
		HttpAwareErrorCode errorCode = exception.getErrorCode();
		String errorCodeMessage = messageTextResolver.resolve(exception, locale);

		log.error("Platform Exception occurred: {}, errorCode: {}, errorMsg: {}",
				exception, exception.getClass().getName(), errorCode.code(), errorCodeMessage);

		return createResponse(errorCode, errorCodeMessage);
	}

	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<ApiBaseResponse> handleInternalServerErrors(RuntimeException exception, Locale locale) {
		HttpAwareErrorCode errorCode = PlatformErrorCodes.INTERNAL_SERVER_ERROR;
		String errorCodeMessage = messageTextResolver.resolve(errorCode, locale);

		log.error("Platform Exception occurred: {}, errorCode: {}, errorMsg: {}",
				exception, exception.getClass().getName(), errorCode.code(), errorCodeMessage);

		return createResponse(errorCode, errorCodeMessage);
	}

	private ResponseEntity<ApiBaseResponse> createResponse(HttpAwareErrorCode errorCode, String errorCodeMessage) {
		return ResponseEntity
				.status(errorCode.httpStatus())
				.body(ApiBaseResponse.builder()
						.operationResult(ApiBaseResponse.OperationResult.builder()
								.returnCode(Integer.toString(errorCode.code()))
								.returnMessage(errorCodeMessage)
								.build())
						.build());
	}

}
