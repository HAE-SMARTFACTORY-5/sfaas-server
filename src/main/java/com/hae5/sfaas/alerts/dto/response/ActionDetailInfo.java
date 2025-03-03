package com.hae5.sfaas.alerts.dto.response;

import com.hae5.sfaas.alerts.model.AlertActionDetail;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActionDetailInfo {
    private String faultType;
    private String actionDetail;
    private String maintenanceStaff;
    private LocalDateTime actionTime;

    public static ActionDetailInfo from(AlertActionDetail detail) {
        return ActionDetailInfo.builder()
                .faultType(detail.getFaultType())
                .actionDetail(detail.getActionDetail())
                .maintenanceStaff(detail.getMaintenanceStaff())
                .actionTime(detail.getActionTime())
                .build();
    }
}