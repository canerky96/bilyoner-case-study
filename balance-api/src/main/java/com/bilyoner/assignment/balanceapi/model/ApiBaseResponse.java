package com.bilyoner.assignment.balanceapi.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiBaseResponse {

    @Builder.Default
    private OperationResult operationResult = new OperationResult("0", "Success");

    @Builder
    @Getter
    @AllArgsConstructor
    public static class OperationResult {

        private String returnCode;
        private String returnMessage;
    }
}
