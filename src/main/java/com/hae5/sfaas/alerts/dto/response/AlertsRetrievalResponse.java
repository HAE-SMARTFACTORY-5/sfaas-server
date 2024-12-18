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
    private String lineId;
    private String processId;
    private String alarmType;
    private String completionStatus;
    private Integer downtime;
    private LocalDateTime alarmTime;

    public static AlertsRetrievalResponse from(AlertsRetrieval alertsRetrieval) {
        return AlertsRetrievalResponse.builder()
                .alarmId(alertsRetrieval.getAlarmId())
                .lineId(alertsRetrieval.getLineId())
                .processId(alertsRetrieval.getProcessId())
                .alarmType(alertsRetrieval.getAlarmType())
                .completionStatus(alertsRetrieval.getCompletionStatus())
                .downtime(alertsRetrieval.getDowntime())
                .alarmTime(alertsRetrieval.getAlarmTime())
                .build();
    }
}
