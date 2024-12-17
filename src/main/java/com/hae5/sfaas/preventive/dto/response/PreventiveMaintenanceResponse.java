package com.hae5.sfaas.preventive.dto.response;

import com.hae5.sfaas.preventive.model.PreventiveMaintenance;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PreventiveMaintenanceResponse {
    private String lineId;
    private String processId;
    private String serialNumber;
    private LocalDateTime plannedDate;
    private Integer estimatedTime;

    public static PreventiveMaintenanceResponse from(PreventiveMaintenance preventiveMaintenance) {
        return PreventiveMaintenanceResponse.builder()
                .lineId(preventiveMaintenance.getLineId())
                .processId(preventiveMaintenance.getProcessId())
                .serialNumber(preventiveMaintenance.getSerialNumber())
                .plannedDate(preventiveMaintenance.getPlannedDate())
                .estimatedTime(preventiveMaintenance.getEstimatedTime())
                .build();
    }
}
