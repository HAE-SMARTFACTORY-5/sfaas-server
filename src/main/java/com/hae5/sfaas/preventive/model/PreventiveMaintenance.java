package com.hae5.sfaas.preventive.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PreventiveMaintenance {

    private Integer maintenanceId;
    private String equipmentId;
    private LocalDateTime plannedDate;
    private LocalDateTime executionDate;
    private String result;
    private Integer estimatedTime;
    private String status;
    private LocalDateTime createdAt;

    public static PreventiveMaintenance create(Integer maintenanceId, String equipmentId, LocalDateTime plannedDate,
                                               LocalDateTime executionDate, String result, Integer estimatedTime,
                                               String status, LocalDateTime createdAt){
        return PreventiveMaintenance.builder()
                .maintenanceId(maintenanceId)
                .equipmentId(equipmentId)
                .plannedDate(plannedDate)
                .executionDate(executionDate)
                .result(result)
                .estimatedTime(estimatedTime)
                .status(status)
                .createdAt(createdAt)
                .build();
    }

}
