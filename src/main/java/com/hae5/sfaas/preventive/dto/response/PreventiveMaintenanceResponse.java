package com.hae5.sfaas.preventive.dto.response;

import com.hae5.sfaas.preventive.model.PreventiveMaintenance;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PreventiveMaintenanceResponse {
    private Integer maintenanceId;
    private String equipmentId; // db 생성 완료 후 설비 Id로 설비명으로 대체하기
    private LocalDateTime plannedDate;
    private LocalDateTime executionDate;
    private String InspectResult;
    private Integer estimatedTime;
    private String status;

    public static PreventiveMaintenanceResponse from(PreventiveMaintenance preventiveMaintenance) {
        return PreventiveMaintenanceResponse.builder()
                .maintenanceId(preventiveMaintenance.getMaintenanceId())
                .equipmentId(preventiveMaintenance.getEquipmentId())
                .plannedDate(preventiveMaintenance.getPlannedDate())
                .executionDate(preventiveMaintenance.getExecutionDate())
                .InspectResult(preventiveMaintenance.getInspectResult())
                .estimatedTime(preventiveMaintenance.getEstimatedTime())
                .status(preventiveMaintenance.getStatus())
                .build();
    }
}
