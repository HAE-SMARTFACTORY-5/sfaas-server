package com.hae5.sfaas.alerts.dto.response;

import com.hae5.sfaas.alerts.model.AlertsRetrieval;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertsRetrievalResponse {
    private String alarmId;
    private LocalDateTime actionStartTime;
    private String lineId;
    private String processId;
    private String alarmType;
    private String actionDetail;
    private String completionStatus;
    private LocalDateTime actionCompletionTime;

    public static AlertsRetrievalResponse from(AlertsRetrieval alertsRetrieval) {
        return AlertsRetrievalResponse.builder()
                .alarmId(alertsRetrieval.getAlarmId())
                .actionStartTime(alertsRetrieval.getActionStartTime())
                .lineId(alertsRetrieval.getLineId())
                .processId(alertsRetrieval.getProcessId())
                .alarmType(alertsRetrieval.getAlarmType())
                .actionDetail(alertsRetrieval.getActionDetail())
                .completionStatus(alertsRetrieval.getCompletionStatus())
                .actionCompletionTime(alertsRetrieval.getActionCompletionTime())
                .build();
    }
}
