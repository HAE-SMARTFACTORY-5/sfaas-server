package com.hae5.sfaas.production.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.production.dto.response.ProductionPerformanceResponse;
import com.hae5.sfaas.production.mapper.ProductionPerformanceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ProductionPerformanceServiceTest extends SfaasApplicationTests {

    @Mock
    private ProductionPerformanceMapper productionPerformanceMapper;

    @InjectMocks
    private ProductionPerformanceService productionPerformanceService;

    @DisplayName("사용자 삭제")
    @Test
    public void deleteUserTest () {
        //given
        List<ProductionPerformanceResponse> response = new ArrayList<>();

        response.add(new ProductionPerformanceResponse("프레스", 10, 9));

        when(productionPerformanceMapper.getTodayPerformance()).thenReturn(response);

        //when
        List<ProductionPerformanceResponse> todayPerformance = productionPerformanceService.getTodayPerformance();

        //then
        assertThat(todayPerformance.size()).isEqualTo(1);
        assertThat(todayPerformance.get(0).getProcessName()).isEqualTo("프레스");
    }

}
