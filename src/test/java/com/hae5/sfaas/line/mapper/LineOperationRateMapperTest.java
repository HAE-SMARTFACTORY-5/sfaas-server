package com.hae5.sfaas.line.mapper;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.line.model.LineOperationRate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LineOperationRateMapperTest extends SfaasApplicationTests {

    @Autowired
    private LineOperationRateMapper lineOperationRateMapper;

    @AfterEach
    public void clear() {
        lineOperationRateMapper.deleteAll();
    }

    @DisplayName("LineOperationRate 저장")
    @Test
    public void saveTest() {
        //given
        LineOperationRate lineOperationRate = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2024)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("종합가동률")
                .build();

        //when
        lineOperationRateMapper.save(lineOperationRate);

        //then
        assertThat(lineOperationRateMapper.findAll().size()).isEqualTo(1);
    }

    @DisplayName("LineOperationRate 전체 삭제")
    @Test
    public void deleteAllTest() {
        //given
        LineOperationRate lineOperationRate = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2024)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("종합가동률")
                .build();
        lineOperationRateMapper.save(lineOperationRate);

        //when
        lineOperationRateMapper.deleteAll();

        //then
        assertThat(lineOperationRateMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("LineOperationRate 전체 조회")
    @Test
    public void findAllTest() {
        //given
        LineOperationRate lineOperationRate1 = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2024)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("종합가동률")
                .build();
        LineOperationRate lineOperationRate2 = LineOperationRate.builder()
                .lineOperationRateId(2L)
                .factoryId(1L)
                .year(2024)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("차이")
                .build();
        lineOperationRateMapper.save(lineOperationRate1);
        lineOperationRateMapper.save(lineOperationRate2);

        //when
        List<LineOperationRate> lineOperationRates = lineOperationRateMapper.findAll();

        //then
        assertThat(lineOperationRates.size()).isEqualTo(2);
    }

    @DisplayName("올해 LineOperationRate 전체 조회")
    @Test
    public void getNowYearLineOperationRateTest() {
        //given
        LineOperationRate lineOperationRate1 = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2023)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("종합가동률")
                .build();
        LineOperationRate lineOperationRate2 = LineOperationRate.builder()
                .lineOperationRateId(2L)
                .factoryId(1L)
                .year(2024)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("차이")
                .build();
        lineOperationRateMapper.save(lineOperationRate1);
        lineOperationRateMapper.save(lineOperationRate2);

        //when
        List<LineOperationRate> nowYearLineOperationRates = lineOperationRateMapper.getNowYearLineOperationRate(1L, 2024);

        //then
        assertThat(nowYearLineOperationRates.size()).isEqualTo(1);
    }

    @DisplayName("올해의 라인 가동률 계획 및 실적 조회")
    @Test
    public void getNowYearLineOperationTest() {
        //given
        LineOperationRate lineOperationRate1 = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2023)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("종합가동률")
                .build();
        LineOperationRate lineOperationRate2 = LineOperationRate.builder()
                .lineOperationRateId(2L)
                .factoryId(1L)
                .year(2024)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId(1L)
                .category("종합가동률")
                .build();
        lineOperationRateMapper.save(lineOperationRate1);
        lineOperationRateMapper.save(lineOperationRate2);

        //when
        List<LineOperationRate> nowYearLineOperationRates = lineOperationRateMapper.getNowYearLineOperation(1L, 2024);

        //then
        assertThat(nowYearLineOperationRates.size()).isEqualTo(1);
    }
}
