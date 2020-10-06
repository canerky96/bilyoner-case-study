package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.exception.BalanceApiException;
import com.bilyoner.assignment.couponapi.exception.PlatformErrorCodes;
import com.bilyoner.assignment.couponapi.model.ApiBaseResponse;
import com.bilyoner.assignment.couponapi.model.BalanceApiRequest;
import com.bilyoner.assignment.couponapi.model.BalanceApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class BalanceApiService {

    private final RestTemplate restTemplate;

    @Value("${balance-api.url}")
    private String url;

    public ApiBaseResponse<BalanceApiResponse> updateBalance(BalanceApiRequest balanceApiRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BalanceApiRequest> entity = new HttpEntity<>(balanceApiRequest, headers);

        ResponseEntity<ApiBaseResponse<BalanceApiResponse>> response = restTemplate.exchange(
                url + "/balance-api/balance",
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<ApiBaseResponse<BalanceApiResponse>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK &&
                response.getBody().getOperationResult().getReturnCode().equals("0")) {
            return response.getBody();
        } else {
            throw new BalanceApiException(PlatformErrorCodes.INVALID_BALANCEAPI_RESPONSE_ERROR,
					String.format("Balanceapi Request Failed StatusCode:%s", response.getStatusCode()));
        }
    }

}
