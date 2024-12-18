package com.hae5.sfaas.line.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.line.dto.response.AllLineOperationRateResponse;
import com.hae5.sfaas.line.dto.response.QuarterLineOperationRateResponse;
import com.hae5.sfaas.line.dto.response.TotalLineOperationResponse;
import com.hae5.sfaas.line.mapper.LineOperationRateMapper;
import com.hae5.sfaas.line.model.LineOperationRate;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LineOperationRateServiceTest extends SfaasApplicationTests {

    @Mock
    private LineOperationRateMapper lineOperationRateMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private LineOperationRateService lineOperationRateService;

    @DisplayName("이번 분기 LineOperationRate 조회")
    @Test
    public void getQuarterLineOperationRateTest () {

        //given
        User user = User.builder()
                .userId(1L)
                .factoryId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        LineOperationRate lineOperationRate = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2023)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId("C_L")
                .category("종합가동률")
                .build();

        List<LineOperationRate> result = new ArrayList<>();
        result.add(lineOperationRate);

        when(lineOperationRateMapper.getNowYearLineOperationRate(any(Long.class), any(Integer.class))).thenReturn(result);
        when(userMapper.findById(any(Long.class))).thenReturn(Optional.ofNullable(user));

        //when
        QuarterLineOperationRateResponse response = lineOperationRateService.getQuarterLineOperationRate(1L);

        //then
        Assertions.assertThat(response.getResult().get("CT").size()).isEqualTo(3);
    }

    @DisplayName("올해 LineOperationRate 조회")
    @Test
    public void getAllLineOperationRateTest () {

        //given
        User user = User.builder()
                .userId(1L)
                .factoryId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        LineOperationRate lineOperationRate = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2023)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId("C_L")
                .category("종합가동률")
                .build();

        List<LineOperationRate> result = new ArrayList<>();
        result.add(lineOperationRate);

        when(lineOperationRateMapper.getNowYearLineOperationRate(any(Long.class), any(Integer.class))).thenReturn(result);
        when(userMapper.findById(any(Long.class))).thenReturn(Optional.ofNullable(user));

        //when
        AllLineOperationRateResponse response = lineOperationRateService.getAllLineOperationRate(1L);

        //then
        Assertions.assertThat(response.getResult().get("CT").size()).isEqualTo(12);
    }

    @DisplayName("올해 라인 가동률의 계획 및 실적 조회")
    @Test
    public void getTotalLineOperationTest () {

        //given
        User user = User.builder()
                .userId(1L)
                .factoryId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        Double expectJanData = 20.0;

        LineOperationRate lineOperationRate1 = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2023)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId("C_L")
                .category("종합가동률")
                .build();
        LineOperationRate lineOperationRate2 = LineOperationRate.builder()
                .lineOperationRateId(1L)
                .factoryId(1L)
                .year(2023)
                .jan(10.0).feb(20.0).mar(30.0).apr(40.0).may(50.0).jun(60.0)
                .jul(70.0).aug(80.0).sep(90.0).oct(100.0).nov(110.0).decem(120.0)
                .total(820.0)
                .createdAt(LocalDateTime.now())
                .processId("CT")
                .lineId("C_L")
                .category("종합가동률")
                .build();

        List<LineOperationRate> result = new ArrayList<>();
        result.add(lineOperationRate1);
        result.add(lineOperationRate2);

        when(lineOperationRateMapper.getNowYearLineOperation(any(Long.class), any(Integer.class))).thenReturn(result);
        when(userMapper.findById(any(Long.class))).thenReturn(Optional.ofNullable(user));

        //when
        TotalLineOperationResponse response = lineOperationRateService.getTotalLineOperation(1L);

        //then
        Assertions.assertThat(response.getActualLineOperations().getLineId()).isEqualTo("C_L");
        Assertions.assertThat(response.getActualLineOperations().getJan()).isEqualTo(expectJanData);
        Assertions.assertThat(response.getPlannedLineOperations()).isNull();
    }

}
