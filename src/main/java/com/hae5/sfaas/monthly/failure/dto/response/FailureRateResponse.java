package com.hae5.sfaas.monthly.failure.dto.response;

import com.hae5.sfaas.monthly.failure.model.MonthlyFailure;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FailureRateResponse {
    private String process;
    private Integer failureMonth;
    private Double failureRatePlan;
    private Double failureRateActual;

    public static FailureRateResponse from(MonthlyFailure monthlyFailure) {
        return FailureRateResponse.builder()
                .process(monthlyFailure.getProcess())
                .failureMonth(monthlyFailure.getFailureMonth())
                .failureRatePlan(monthlyFailure.getFailureRatePlan())
                .failureRateActual(monthlyFailure.getFailureRateActual())
                .build();
    }
}
