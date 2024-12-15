package com.hae5.sfaas.alerts.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertAction {
    private String alarmId;
    private String faultDetail;
    private String maintenanceStaff;
    private String actionDetails;
    private LocalDateTime actionStartTime;
    private LocalDateTime actionCompletionTime;
    private Integer downtime;
    private String completionStatus;

    public static AlertAction create(String alarmId, String faultDetail,
            String maintenanceStaff, String actionDetails,
            LocalDateTime actionStartTime, LocalDateTime actionCompletionTime,
            Integer downtime, String completionStatus) {
        return AlertAction.builder()
                .alarmId(alarmId)
                .faultDetail(faultDetail)
                .maintenanceStaff(maintenanceStaff)
                .actionDetails(actionDetails)
                .actionStartTime(actionStartTime)
                .actionCompletionTime(actionCompletionTime)
                .downtime(downtime)
                .completionStatus(completionStatus)
                .build();
    }
}
