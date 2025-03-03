package com.hae5.sfaas.quality.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OtherQualityDefectsResponse {

    private LocalDate occurrenceDate;
    private String factoryName;
    private String model;
    private String cause;
    private Boolean resolveStatus;

}
