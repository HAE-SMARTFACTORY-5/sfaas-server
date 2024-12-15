package com.hae5.sfaas.maintenance.schedule.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Schedule {

    private Integer id;
    private String line;
    private String process;
    private String machine;
    private String contents;
    private String remarks;

    public static Schedule create(Integer id, String line, String process,
            String machine, String contents, String remarks) {
        return Schedule.builder()
                .id(id)
                .line(line)
                .process(process)
                .machine(machine)
                .contents(contents)
                .remarks(remarks)
                .build();
    }
}
