package com.hae5.sfaas.fault.dto.response;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import com.hae5.sfaas.fault.model.Fault;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FaultResponse {
    private String line;
    private String process;
    private String alarmType;
    private LocalDateTime alarmTime;

    public static FaultResponse from(Fault fault) {
        return FaultResponse.builder()
                .line(fault.getLine())
                .process(fault.getProcess())
                .alarmType(fault.getAlarmType())
                .alarmTime(fault.getAlarmTime())
                .build();
    }
}
