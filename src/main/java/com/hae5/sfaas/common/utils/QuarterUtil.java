package com.hae5.sfaas.common.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuarterUtil {

    private static final HashMap<Integer, List<String>> QUARTER_MONTHS = new HashMap<>();

    // 정적 초기화 블록을 통해 HashMap 초기화
    static {
        QUARTER_MONTHS.put(1, List.of("jan", "feb", "mar"));
        QUARTER_MONTHS.put(2, List.of("apr", "may", "jun"));
        QUARTER_MONTHS.put(3, List.of("jul", "aug", "sep"));
        QUARTER_MONTHS.put(4, List.of("oct", "nov", "decem"));
    }

    public static int getNowQuarter() {
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // 현재 월 가져오기
        int currentMonth = currentDate.getMonthValue();

        // 분기 계산
        int quarter = (currentMonth - 1) / 3 + 1;

        // 결과 출력
        return quarter;
    }

    public static List<String> getQuarterMonths(int quarter) {
        return QUARTER_MONTHS.get(quarter);
    }

    public static List<String> getAllMonths() {
        List<String> allMonth = new ArrayList<>();
        for (Integer integer : QUARTER_MONTHS.keySet()) {
            allMonth.addAll(QUARTER_MONTHS.get(integer));
        }
        return allMonth;
    }

}
