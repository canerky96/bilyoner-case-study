package com.bilyoner.assignment.couponapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ApiBaseResponse<T> {

	@JsonProperty("meta")
	@Builder.Default
	private OperationResult operationResult = new OperationResult("0", "Success");

	@JsonProperty("data")
	private T operationResultData;

	@Builder
	@Getter
	@AllArgsConstructor
	public static class OperationResult {

		private String returnCode;
		private String returnMessage;
	}
}
