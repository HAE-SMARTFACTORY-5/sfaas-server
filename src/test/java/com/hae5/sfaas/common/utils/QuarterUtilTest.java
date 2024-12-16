package com.hae5.sfaas.common.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuarterUtilTest {

    @DisplayName("현재 분기 조회")
    @Test
    public void getNowQuarterTest() {
        //given
        Integer expectQuarter = 4;

        //when
        int nowQuarter = QuarterUtil.getNowQuarter(LocalDate.of(2024, 12, 1));

        //then
        Assertions.assertThat(nowQuarter).isEqualTo(expectQuarter);
    }

    @DisplayName("현재 분기 월 조회")
    @Test
    public void getQuarterMonthsTest() {
        //given
        List<String> expectMonths = List.of("jan", "feb", "mar");

        //when
        List<String> quarterMonths = QuarterUtil.getQuarterMonths(1);

        //then
        Assertions.assertThat(quarterMonths).isEqualTo(expectMonths);
    }

    @DisplayName("모든 분기의 월 조회")
    @Test
    public void getAllMonthsTest() {
        //given
        List<String> expectMonths = List.of("jan", "feb", "mar",
                                                        "apr", "may", "jun",
                                                        "jul", "aug", "sep",
                                                        "oct", "nov", "decem");

        //when
        List<String> allMonths = QuarterUtil.getAllMonths();

        //then
        Assertions.assertThat(allMonths).isEqualTo(expectMonths);
    }

}
