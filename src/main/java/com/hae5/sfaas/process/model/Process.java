package com.hae5.sfaas.process.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Process {

    private Long processId;
    private String processName;
    private String description;

    public static Process create(Long processId, String processName, String description) {
        return Process.builder()
                .processId(processId)
                .processName(processName)
                .description(description)
                .build();
    }

}
