package com.hae5.sfaas.alerts.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertsRetrieval {
    private String alarmId;
    private LocalDateTime actionStartTime;
    private String lineId;
    private String processId;
    private String alarmType;
    private String actionDetail;
    private String completionStatus;
    private LocalDateTime actionCompletionTime;

    public static AlertsRetrieval create(
            String alarmId,
            LocalDateTime actionStartTime,
            String lineId,
            String processId,
            String alarmType,
            String actionDetail,
            String completionStatus,
            LocalDateTime actionCompletionTime) {
        return AlertsRetrieval.builder()
                .alarmId(alarmId)
                .actionStartTime(actionStartTime)
                .lineId(lineId)
                .processId(processId)
                .alarmType(alarmType)
                .actionDetail(actionDetail)
                .completionStatus(completionStatus)
                .actionCompletionTime(actionCompletionTime)
                .build();
    }
}
