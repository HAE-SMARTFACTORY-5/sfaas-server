package com.hae5.sfaas.production.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProcessPerformance {

    private String processName;
    private Integer plannedQuantity;
    private Integer actualQuantity;

}
