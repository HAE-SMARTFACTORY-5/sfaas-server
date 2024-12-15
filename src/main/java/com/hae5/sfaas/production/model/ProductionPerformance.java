package com.hae5.sfaas.production.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductionPerformance {

    private Long performanceId;
    private Long lineId;
    private LocalDate date;
    private Integer plannedQuantity;
    private Integer actualQuantity;
    private LocalDateTime createdAt;
    private String shift;
    private Double operatingRate;
    private String processId;

    public static ProductionPerformance create (Long performanceId, Long lineId, LocalDate date, Integer plannedQuantity,
                                                Integer actualQuantity, LocalDateTime createdAt, String shift,
                                                Double operatingRate, String processId) {
        return ProductionPerformance.builder()
                .performanceId(performanceId)
                .lineId(lineId)
                .date(date)
                .plannedQuantity(plannedQuantity)
                .actualQuantity(actualQuantity)
                .createdAt(createdAt)
                .shift(shift)
                .operatingRate(operatingRate)
                .processId(processId)
                .build();
    }

}
