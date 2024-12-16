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
public class QuarterLineOperationRateResponse {
    private List<String> quarterMonths;
    private Map<String, List<MonthlyLineOperationRateResponse>> result;

    public static QuarterLineOperationRateResponse of(List<String> quarterMonths, Map<String, List<MonthlyLineOperationRateResponse>> result) {
        return QuarterLineOperationRateResponse.builder()
                .quarterMonths(quarterMonths)
                .result(result)
                .build();
    }
}
