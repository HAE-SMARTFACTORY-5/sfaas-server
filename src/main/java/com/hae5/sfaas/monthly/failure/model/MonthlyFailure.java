package com.hae5.sfaas.monthly.failure.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MonthlyFailure {

    private String id;
    private String process;
    private Integer failureMonth;
    private Double failureRatePlan;
    private Double failureRateActual;
    private Double mtbf;
    private Double mttr;
    private Double operationTime;
    private Integer faultNum;
    private Double downtime;

    public static MonthlyFailure create(String id, String process, Integer failureMonth, Double failureRatePlan, Double failureRateActual,
                                        Double mtbf, Double mttr, Double operationTime, Integer faultNum, Double downtime){
        return MonthlyFailure.builder()
                .id(id)
                .process(process)
                .failureMonth(failureMonth)
                .failureRatePlan(failureRatePlan)
                .failureRateActual(failureRateActual)
                .mtbf(mtbf)
                .mttr(mttr)
                .operationTime(operationTime)
                .faultNum(faultNum)
                .downtime(downtime)
                .build();
    }

}
