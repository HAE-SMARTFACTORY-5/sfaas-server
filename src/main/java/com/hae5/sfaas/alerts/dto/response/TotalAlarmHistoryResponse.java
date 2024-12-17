package com.hae5.sfaas.alerts.dto.response;

import lombok.Getter;

@Getter
public class TotalAlarmHistoryResponse {

    private Integer jan;
    private Integer feb;
    private Integer mar;
    private Integer apr;
    private Integer may;
    private Integer jun;
    private Integer jul;
    private Integer aug;
    private Integer sep;
    private Integer oct;
    private Integer nov;
    private Integer decem;

    public TotalAlarmHistoryResponse() {
        this.jan = 0;
        this.feb = 0;
        this.mar = 0;
        this.apr = 0;
        this.may = 0;
        this.jun = 0;
        this.jul = 0;
        this.aug = 0;
        this.sep = 0;
        this.oct = 0;
        this.nov = 0;
        this.decem = 0;
    }

    public void addMonthValue(Integer month, Integer value) {
        switch (month) {
            case 1 -> {this.jan = value; break;}
            case 2 -> {this.feb = value; break;}
            case 3 -> {this.mar = value; break;}
            case 4 -> {this.apr = value; break;}
            case 5 -> {this.may = value; break;}
            case 6 -> {this.jun = value; break;}
            case 7 -> {this.jul = value; break;}
            case 8 -> {this.aug = value; break;}
            case 9 -> {this.sep = value; break;}
            case 10 -> {this.oct = value; break;}
            case 11 -> {this.nov = value; break;}
            case 12 -> {this.decem = value; break;}
        }

    }
}
