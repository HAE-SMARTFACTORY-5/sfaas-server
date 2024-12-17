package com.hae5.sfaas.spareParts.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import com.hae5.sfaas.spareParts.mapper.SparePartsMapper;
import com.hae5.sfaas.spareParts.model.SpareParts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SparePartsServiceTest extends SfaasApplicationTests {

    @Autowired
    private SparePartsService sparePartsService;

    @Autowired
    private SparePartsMapper sparePartsMapper;

    @BeforeEach
    void setUp() {
        sparePartsMapper.deleteAll();
    }

    @AfterEach
    void cleanup() {
        sparePartsMapper.deleteAll();
    }

    @Test
    @DisplayName("예비 부품 목록 조회 테스트")
    void getSpareParts() {
        // given
        SpareParts sparePart = SpareParts.create(
                "ELC-001",
                "Cable Harness",
                "A109",
                11,
                12,
                -1,
                2461.89,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());

        sparePartsMapper.save(sparePart);

        // when
        SparePartsResponse response = sparePartsService.getSpareParts();

        // then
        assertThat(response).isNotNull();
        assertThat(response.getChartTitle()).isEqualTo("ProductGroup0");
        assertThat(response.getChartType()).isEqualTo("bar");
        assertThat(response.getXAxis().getKey()).isEqualTo("ProductName");
        assertThat(response.getXAxis().getLabel()).hasSize(1);
        assertThat(response.getXAxis().getLabel().get(0)).isEqualTo("ELC-001");
        assertThat(response.getYAxis().getKey()).isEqualTo("ProductOutput");
        assertThat(response.getYAxis().getValue()).hasSize(1);
        assertThat(response.getYAxis().getValue().get(0)).isEqualTo(11);
    }

    @Test
    @DisplayName("예비 부품이 없을 경우 빈 차트 데이터 반환 테스트")
    void getSpareParts_ReturnsEmptyChart() {
        // when
        SparePartsResponse response = sparePartsService.getSpareParts();

        // then
        assertThat(response).isNotNull();
        assertThat(response.getChartTitle()).isEqualTo("ProductGroup0");
        assertThat(response.getChartType()).isEqualTo("bar");
        assertThat(response.getXAxis().getLabel()).isEmpty();
        assertThat(response.getYAxis().getValue()).isEmpty();
    }
}