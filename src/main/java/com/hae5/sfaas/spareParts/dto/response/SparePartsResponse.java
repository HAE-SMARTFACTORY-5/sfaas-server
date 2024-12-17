package com.hae5.sfaas.spareParts.dto.response;


import com.hae5.sfaas.spareParts.model.SpareParts;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SparePartsResponse {
    private String chartTitle;
    private String chartType;
    private XAxis xAxis;
    private YAxis yAxis;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class XAxis {
        private String key;
        private List<String> label;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class YAxis {
        private String key;
        private List<Integer> value;
    }

    public static SparePartsResponse from(List<SpareParts> spareParts) {
        return SparePartsResponse.builder()
                .chartTitle("ProductGroup0")
                .chartType("bar")
                .xAxis(XAxis.builder()
                        .key("ProductName")
                        .label(spareParts.stream()
                                .map(SpareParts::getItemCode)
                                .toList())
                        .build())
                .yAxis(YAxis.builder()
                        .key("ProductOutput")
                        .value(spareParts.stream()
                                .map(SpareParts::getStock)
                                .toList())
                        .build())
                .build();
    }
}

