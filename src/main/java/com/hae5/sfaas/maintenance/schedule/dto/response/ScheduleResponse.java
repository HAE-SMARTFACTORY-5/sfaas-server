package com.hae5.sfaas.maintenance.schedule.dto.response;

import com.hae5.sfaas.maintenance.schedule.model.Schedule;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleResponse {
    private Integer id;
    private String line;
    private String machine;
    private String process;
    private String contents;
    private String remarks;

    public static ScheduleResponse from(Schedule schedule){
        return ScheduleResponse.builder()
                .id(schedule.getId())
                .line(schedule.getLine())
                .machine(schedule.getMachine())
                .process(schedule.getProcess())
                .contents(schedule.getContents())
                .remarks(schedule.getRemarks())
                .build();
    }
}
