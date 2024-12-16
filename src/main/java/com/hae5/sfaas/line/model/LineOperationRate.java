package com.hae5.sfaas.line.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LineOperationRate {

    private Long lineOperationRateId;
    private Long factoryId;
    private Integer year;
    private Double jan;
    private Double feb;
    private Double mar;
    private Double apr;
    private Double may;
    private Double aug;
    private Double oct;
    private Double nov;
    private Double decem;
    private Double total;
    private LocalDateTime createdAt;
    private String processId;
    private Long lineId;
    private String category;
    private Double jun;
    private Double jul;
    private Double sep;
    
    public static LineOperationRate create(Long lineOperationRateId, Long factoryId, Integer year, Double jan, Double feb,
                                            Double mar, Double apr, Double may, Double aug, Double oct, Double nov, Double decem,
                                           Double total, LocalDateTime createdAt, String processId, Long lineId, String category, 
                                           Double jun, Double jul, Double sep) {
        return LineOperationRate.builder()
                .lineOperationRateId(lineOperationRateId)
                .factoryId(factoryId)
                .year(year)
                .jan(jan)
                .feb(feb)
                .mar(mar)
                .apr(apr)
                .may(may)
                .aug(aug)
                .oct(oct)
                .nov(nov)
                .decem(decem)
                .total(total)
                .createdAt(createdAt)
                .processId(processId)
                .lineId(lineId)
                .category(category)
                .jun(jun)
                .jul(jul)
                .sep(sep)
                .build();
    }


    public Double getValueByMonth(String month) {
        switch (month) {
            case "jan" -> {return this.jan;}
            case "feb" -> {return this.feb;}
            case "mar" -> {return this.mar;}
            case "apr" -> {return this.apr;}
            case "may" -> {return this.may;}
            case "jun" -> {return this.jun;}
            case "jul" -> {return this.jul;}
            case "aug" -> {return this.aug;}
            case "sep" -> {return this.sep;}
            case "oct" -> {return this.oct;}
            case "nov" -> {return this.nov;}
            case "decem" -> {return this.decem;}
            default -> {return null;}
        }
    }

}
