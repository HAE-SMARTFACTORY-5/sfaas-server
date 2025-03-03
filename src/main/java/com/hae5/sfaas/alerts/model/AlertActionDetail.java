package com.hae5.sfaas.alerts.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertActionDetail {
    private Long alertActionId;
    private String alarmId;
    private String faultType;
    private String actionDetail;
    private String maintenanceStaff;
    private LocalDateTime actionTime;
    private Integer actionSequence;
    private LocalDateTime createdAt;

    public static AlertActionDetail create(
            String alarmId,
            String faultType,
            String actionDetail,
            String maintenanceStaff,
            LocalDateTime actionTime,
            Integer actionSequence) {
        return AlertActionDetail.builder()
                .alarmId(alarmId)
                .faultType(faultType)
                .actionDetail(actionDetail)
                .maintenanceStaff(maintenanceStaff)
                .actionTime(actionTime)
                .actionSequence(actionSequence)
                .build();
    }
}
