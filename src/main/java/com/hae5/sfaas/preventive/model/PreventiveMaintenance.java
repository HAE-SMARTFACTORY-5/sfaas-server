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
    private String lineId;
    private String processId;
    private String serialNumber;
    private LocalDateTime plannedDate;
    private LocalDateTime executionDate;
    private String inspectResult;
    private Integer estimatedTime;
    private String status;
    private LocalDateTime createdAt;
    private String inspectionName;

    public static PreventiveMaintenance create(Integer maintenanceId, String equipmentId, String lineId,
            String processId,
            String serialNumber, LocalDateTime plannedDate, LocalDateTime executionDate, String inspectResult,
            Integer estimatedTime, String status, LocalDateTime createdAt, String inspectionName) {
        return PreventiveMaintenance.builder()
                .maintenanceId(maintenanceId)
                .equipmentId(equipmentId)
                .lineId(lineId)
                .processId(processId)
                .serialNumber(serialNumber)
                .plannedDate(plannedDate)
                .executionDate(executionDate)
                .inspectResult(inspectResult)
                .estimatedTime(estimatedTime)
                .status(status)
                .createdAt(createdAt)
                .inspectionName(inspectionName)
                .build();
    }

}
