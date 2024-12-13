package com.hae5.sfaas.fault.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Fault {
    private String alarmId;
    private String line;
    private String process;
    private String alarmType;
    private LocalDateTime alarmTime;

    // 테스트 코드 작성에 용이하도록 작성
    public static Fault create(String alarmId, String line, String process, String alarmType,
            LocalDateTime alarmTime) {
        return Fault.builder()
                .alarmId(alarmId)
                .line(line)
                .process(process)
                .alarmType(alarmType)
                .alarmTime(alarmTime)
                .build();
    }
}
