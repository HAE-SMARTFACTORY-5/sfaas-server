package com.hae5.sfaas.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AlarmCategoryL2 {
    private String categoryL2Id;
    private String categoryL1Id;
    private String processId;
    private String description;
}
