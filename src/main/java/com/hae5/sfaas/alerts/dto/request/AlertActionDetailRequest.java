package com.hae5.sfaas.alerts.dto.request;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertActionDetailRequest {
    private String faultType;
    private String actionDetail;
    private String maintenanceStaff;
    private LocalDateTime actionTime;

    public static AlertActionDetailRequest from(
            String faultType,
            String actionDetail,
            String maintenanceStaff,
            LocalDateTime actionTime) {
        return AlertActionDetailRequest.builder()
                .faultType(faultType)
                .actionDetail(actionDetail)
                .maintenanceStaff(maintenanceStaff)
                .actionTime(actionTime)
                .build();
    }
}