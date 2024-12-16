package com.hae5.sfaas.line.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LineOperationRateTest {

    @DisplayName("String 값에 따라 해당 월의 값 조회")
    @Test
    public void getValueByMonthTest() {
        //given
        Double expectValue = 10.0;
        LineOperationRate lineOperationRate = LineOperationRate.builder()
                .jan(expectValue)
                .feb(20.0)
                .build();

        //when
        Double janValue = lineOperationRate.getValueByMonth("jan");

        //then
        Assertions.assertThat(janValue).isEqualTo(expectValue);
    }

}
