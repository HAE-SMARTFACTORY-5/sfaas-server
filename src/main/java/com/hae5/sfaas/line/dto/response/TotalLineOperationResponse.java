package com.hae5.sfaas.line.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalLineOperationResponse {
    private LineOperationRateResponse plannedLineOperations;
    private LineOperationRateResponse actualLineOperations;
    public static TotalLineOperationResponse of(LineOperationRateResponse plannedLineOperations,
                                                LineOperationRateResponse actualLineOperations) {
        return TotalLineOperationResponse.builder()
                .plannedLineOperations(plannedLineOperations)
                .actualLineOperations(actualLineOperations)
                .build();
    }

}