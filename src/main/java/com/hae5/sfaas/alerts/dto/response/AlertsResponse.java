package com.hae5.sfaas.alerts.dto.response;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import com.hae5.sfaas.alerts.model.Alerts;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertsResponse {
    private String alarmId;
    private String line;
    private String process;
    private String alarmType;
    private LocalDateTime alarmTime;

    public static AlertsResponse from(Alerts alerts) {
        return AlertsResponse.builder()
                .alarmId(alerts.getAlarmId())
                .line(alerts.getLine())
                .process(alerts.getProcess())
                .alarmType(alerts.getAlarmType())
                .alarmTime(alerts.getAlarmTime())
                .build();
    }
}
