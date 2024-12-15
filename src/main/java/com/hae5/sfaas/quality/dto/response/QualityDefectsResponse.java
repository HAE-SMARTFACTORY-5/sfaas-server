package com.hae5.sfaas.quality.dto.response;

import com.hae5.sfaas.quality.model.QualityDefects;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QualityDefectsResponse {

    private LocalDate occurrenceDate;
    private String model;
    private String cause;
    private Boolean resolveStatus;

    public static QualityDefectsResponse from(QualityDefects qualityDefects) {
        return QualityDefectsResponse.builder()
                .occurrenceDate(qualityDefects.getDate())
                .model(qualityDefects.getModel())
                .cause(qualityDefects.getCause())
                .resolveStatus(qualityDefects.getResolved())
                .build();
    }
}
