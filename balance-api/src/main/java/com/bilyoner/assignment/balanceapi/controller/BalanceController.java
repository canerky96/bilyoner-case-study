package com.bilyoner.assignment.balanceapi.controller;

import javax.validation.Valid;

import com.bilyoner.assignment.balanceapi.model.ApiBaseResponse;
import com.bilyoner.assignment.balanceapi.model.BalanceApiRequest;
import com.bilyoner.assignment.balanceapi.model.BalanceApiResponse;
import com.bilyoner.assignment.balanceapi.service.BalanceService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BalanceController {

	private final BalanceService balanceService;

	@PutMapping("/balance")
	public ApiBaseResponse<BalanceApiResponse> updateBalance(@Valid @RequestBody BalanceApiRequest balanceApiRequest) {
		return ApiBaseResponse.<BalanceApiResponse>builder()
				.operationResultData(balanceService.updateBalance(balanceApiRequest))
				.build();
	}

}
