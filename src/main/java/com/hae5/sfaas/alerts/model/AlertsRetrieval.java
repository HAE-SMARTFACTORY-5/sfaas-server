package com.hae5.sfaas.alerts.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertsRetrieval {
    private String alarmId;
    private String lineId;
    private String processId;
    private String alarmType;
    private String completionStatus;
    private Integer downtime;
    private LocalDateTime alarmTime;

    public static AlertsRetrieval create(
            String alarmId,
            String lineId,
            String processId,
            String alarmType,
            String completionStatus,
            Integer downtime,
            LocalDateTime alarmTime) {
        return AlertsRetrieval.builder()
                .alarmId(alarmId)
                .alarmTime(alarmTime)
                .lineId(lineId)
                .processId(processId)
                .alarmType(alarmType)
                .completionStatus(completionStatus)
                .downtime(downtime)
                .build();
    }
}
