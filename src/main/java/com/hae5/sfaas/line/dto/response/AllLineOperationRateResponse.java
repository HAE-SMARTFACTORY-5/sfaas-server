package com.hae5.sfaas.line.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllLineOperationRateResponse {
    private Map<String, List<MonthlyLineOperationRateResponse>> result;

    public static AllLineOperationRateResponse from(Map<String, List<MonthlyLineOperationRateResponse>> result) {
        return AllLineOperationRateResponse.builder()
                .result(result)
                .build();
    }

}