package com.hae5.sfaas.spareParts.dto.response;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hae5.sfaas.spareParts.model.SpareParts;
import lombok.*;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SparePartsResponse {
    private String chartTitle;
    private String chartType;
    @JsonIgnore
    private XAxis xAxis;
    @JsonIgnore
    private YAxis yAxis;

    public String getChartTitle() {
        return chartTitle;
    }

    public String getChartType() {
        return chartType;
    }

    @JsonProperty("xAxis")
    public XAxis getxAxis() {
        return xAxis;
    }

    @JsonProperty("yAxis")
    public YAxis getyAxis() {
        return yAxis;
    }

    //   Deprecated
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

    public static SparePartsResponse of(List<Integer> values, String key) {
        return SparePartsResponse.builder()
                .chartTitle("line")
                .chartType("bar")
                .xAxis(XAxis.builder()
                        .key(key)
                        .label(List.of("Press", "Welding", "Paintin", "Assembly"))
                        .build())
                .yAxis(YAxis.builder()
                        .key("state")
                        .value(values)
                        .build())
                .build();
    }
}

