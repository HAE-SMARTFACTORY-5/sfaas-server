package com.hae5.sfaas.alerts.dto.response;

import com.hae5.sfaas.alerts.model.AlertAction;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertActionResponse {
    private String alarmId;
    private String faultDetail;
    private String maintenanceStaff;
    private String actionDetails;
    private LocalDateTime actionStartTime;
    private LocalDateTime actionCompletionTime;
    private Integer downtime;
    private String completionStatus;

    public static AlertActionResponse from(AlertAction alertAction) {
        return AlertActionResponse.builder()
                .alarmId(alertAction.getAlarmId())
                .faultDetail(alertAction.getFaultDetail())
                .maintenanceStaff(alertAction.getMaintenanceStaff())
                .actionDetails(alertAction.getActionDetails())
                .actionStartTime(alertAction.getActionStartTime())
                .actionCompletionTime(alertAction.getActionCompletionTime())
                .downtime(alertAction.getDowntime())
                .completionStatus(alertAction.getCompletionStatus())
                .build();
    }
}
