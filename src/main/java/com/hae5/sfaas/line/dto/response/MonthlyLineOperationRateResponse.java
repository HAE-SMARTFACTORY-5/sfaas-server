package com.hae5.sfaas.line.dto.response;

import lombok.Getter;


@Getter
public class MonthlyLineOperationRateResponse {
    private String month;
    private Double planned;
    private Double actual;
    private Double difference;
    private Double uptime;
    private Double uph;

    public MonthlyLineOperationRateResponse(String month) {
        this.month = month;
        this.planned= 0.0;
        this.actual= 0.0;
        this.difference= 0.0;
        this.uptime= 0.0;
        this.uph= 0.0;
    }

    public void addCategoryValue(String category, Double value) {
        switch (category) {
            case "사업계획" -> {this.planned = value; break;}
            case "종합가동률" -> {this.actual = value; break;}
            case "차이" -> {this.difference = value; break;}
            case "가동시간" -> {this.uptime = value; break;}
            case "UPH" -> {this.uph = value; break;}
        }
    }
}
