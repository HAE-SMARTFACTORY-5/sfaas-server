package com.hae5.sfaas.quality.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QualityDefects {

    private LocalDate date;
    private String model;
    private String defectType;
    private Boolean resolved;
    private Integer defectiveQuantity;
    private String shift;
    private String cause;
    private String action;
    private Long factoryId;
    private LocalDate resolvedDate;

    public static QualityDefects create(LocalDate date, String model, String defectType, Boolean resolved,
                                        Integer defectiveQuantity, String shift, String cause, String action,
                                        Long factoryId, LocalDate resolvedDate) {
        return QualityDefects.builder()
                .date(date)
                .model(model)
                .defectType(defectType)
                .resolved(resolved)
                .defectiveQuantity(defectiveQuantity)
                .shift(shift)
                .cause(cause)
                .action(action)
                .factoryId(factoryId)
                .resolvedDate(resolvedDate)
                .build();
    }

}
