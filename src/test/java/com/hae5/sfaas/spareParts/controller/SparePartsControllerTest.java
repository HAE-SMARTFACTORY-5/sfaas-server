package com.hae5.sfaas.spareParts.controller;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.spareParts.mapper.SparePartsMapper;
import com.hae5.sfaas.spareParts.model.SpareParts;
import com.hae5.sfaas.user.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class SparePartsControllerTest extends SfaasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SparePartsMapper sparePartsMapper;

    @MockitoBean
    private JwtProvider jwtProvider;

    private AccessTokenInfo accessTokenInfo;

    @BeforeEach
    void setUp() {
        accessTokenInfo = AccessTokenInfo.of("1", UserRole.MEMBER.name());
        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
    }

    @AfterEach
    void cleanup() {
        sparePartsMapper.deleteAll();
    }

    @Test
    @DisplayName("예비 부품 목록 조회 테스트")
    void getSpareParts() throws Exception {
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

        // when & then
        mockMvc.perform(get("/api/v1/spare-parts")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.chartTitle").value("ProductGroup0"))
                .andExpect(jsonPath("$.data.chartType").value("bar"))
                .andExpect(jsonPath("$.data.xaxis.key").value("ProductName"))
                .andExpect(jsonPath("$.data.xaxis.label[0]").value("ELC-001"))
                .andExpect(jsonPath("$.data.yaxis.key").value("ProductOutput"))
                .andExpect(jsonPath("$.data.yaxis.value[0]").value(11));
    }

    @Test
    @DisplayName("예비 부품이 없을 경우 빈 차트 데이터 반환 테스트")
    void getSpareParts_ReturnsEmptyChart() throws Exception {
        // when & then
        mockMvc.perform(get("/api/v1/spare-parts")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.chartTitle").value("ProductGroup0"))
                .andExpect(jsonPath("$.data.chartType").value("bar"))
                .andExpect(jsonPath("$.data.xaxis.label").isEmpty())
                .andExpect(jsonPath("$.data.yaxis.value").isEmpty());
    }
}