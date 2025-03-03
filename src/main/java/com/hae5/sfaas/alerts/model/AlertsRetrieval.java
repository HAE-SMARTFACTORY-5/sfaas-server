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
    private String actionDetail;
}
