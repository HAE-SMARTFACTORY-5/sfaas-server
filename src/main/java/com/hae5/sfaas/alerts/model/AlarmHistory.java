package com.hae5.sfaas.alerts.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlarmHistory {
    private Long alarmId;
    private String lineId;
    private String processId;
    private Long categoryL3Id;
    private LocalDateTime occurrenceTime;
    private LocalDateTime resolutionTime;
    private String status;
    private String cause;
    private String actionTaken;
    private LocalDateTime createdAt;
    
    public static AlarmHistory create(Long alarmId, String lineId, String processId, Long categoryL3Id,
                                        LocalDateTime occurrenceTime, LocalDateTime resolutionTime, String status,
                                        String cause, String actionTaken, LocalDateTime createdAt) {
        return AlarmHistory.builder()
                .alarmId(alarmId)
                .lineId(lineId)
                .processId(processId)
                .categoryL3Id(categoryL3Id)
                .occurrenceTime(occurrenceTime)
                .resolutionTime(resolutionTime)
                .status(status)
                .cause(cause)
                .actionTaken(actionTaken)
                .createdAt(createdAt)
                .build();
    }

}
