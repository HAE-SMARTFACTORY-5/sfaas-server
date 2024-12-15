package com.hae5.sfaas.production.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductionPerformanceResponse {

    private String processName;
    private Integer plannedQuantity;
    private Integer actualQuantity;

}
