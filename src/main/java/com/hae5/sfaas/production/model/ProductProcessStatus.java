package com.hae5.sfaas.production.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductProcessStatus {
    private Long productProcessStatusId;
    private String productId;
    private String lineId;
    private Integer sequence;

    public static ProductProcessStatus create(String productId, String lineId, Integer sequence) {
        return ProductProcessStatus.builder()
                .productId(productId)
                .lineId(lineId)
                .sequence(sequence)
                .build();
    }
}
