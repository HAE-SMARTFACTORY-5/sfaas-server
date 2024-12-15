package com.hae5.sfaas.production.mapper;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.process.mapper.ProcessMapper;
import com.hae5.sfaas.production.dto.response.ProductionPerformanceResponse;
import com.hae5.sfaas.production.model.ProductionPerformance;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductionPerformanceMapperTest extends SfaasApplicationTests {

    @Autowired
    private ProductionPerformanceMapper productionPerformanceMapper;

    @Autowired
    private ProcessMapper processMapper;

    @AfterEach
    public void clear() {
        productionPerformanceMapper.deleteAll();
    }

    @DisplayName("ProductionPerformance 저장")
    @Test
    public void saveTest() {
        //given
        ProductionPerformance productionPerformance = ProductionPerformance.builder()
                .performanceId(100L)
                .date(LocalDate.now())
                .plannedQuantity(20)
                .actualQuantity(10)
                .build();

        //when
        productionPerformanceMapper.save(productionPerformance);

        //then
        assertThat(productionPerformanceMapper.findAll().size()).isEqualTo(1);
    }

    @DisplayName("ProductionPerformance 전체 삭제")
    @Test
    public void deleteAllTest() {
        //given
        ProductionPerformance productionPerformance = ProductionPerformance.builder()
                .performanceId(100L)
                .date(LocalDate.now())
                .plannedQuantity(20)
                .actualQuantity(10)
                .build();
        productionPerformanceMapper.save(productionPerformance);

        //when
        productionPerformanceMapper.deleteAll();

        //then
        assertThat(productionPerformanceMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("ProductionPerformance 전체 조회")
    @Test
    public void findAllTest() {
        //given
        ProductionPerformance productionPerformance1 = ProductionPerformance.builder()
                .performanceId(100L)
                .date(LocalDate.now())
                .plannedQuantity(20)
                .actualQuantity(10)
                .build();
        ProductionPerformance productionPerformance2 = ProductionPerformance.builder()
                .performanceId(101L)
                .date(LocalDate.now())
                .plannedQuantity(20)
                .actualQuantity(10)
                .build();
        productionPerformanceMapper.save(productionPerformance1);
        productionPerformanceMapper.save(productionPerformance2);

        //when
        List<ProductionPerformanceResponse> performanceResponses = productionPerformanceMapper.findAll();

        //then
        assertThat(performanceResponses.size()).isEqualTo(2);
    }

    @DisplayName("오늘에 해당하는 ProductionPerformance 전체 조회")
    @Test
    public void getTodayPerformanceTest() {
        //given
        ProductionPerformance productionPerformance1 = ProductionPerformance.builder()
                .performanceId(100L)
                .date(LocalDate.now().minusDays(1))
                .plannedQuantity(20)
                .actualQuantity(10)
                .build();
        ProductionPerformance productionPerformance2 = ProductionPerformance.builder()
                .performanceId(101L)
                .date(LocalDate.now())
                .plannedQuantity(20)
                .actualQuantity(10)
                .build();
        productionPerformanceMapper.save(productionPerformance1);
        productionPerformanceMapper.save(productionPerformance2);

        //when
        List<ProductionPerformanceResponse> performanceResponses = productionPerformanceMapper.getTodayPerformance();

        //then
        assertThat(performanceResponses.size()).isEqualTo(1);
    }

}
