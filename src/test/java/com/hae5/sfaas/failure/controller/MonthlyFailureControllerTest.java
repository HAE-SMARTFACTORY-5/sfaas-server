package com.hae5.sfaas.failure.controller;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.monthly.failure.mapper.MonthlyFailureMapper;
import com.hae5.sfaas.monthly.failure.model.MonthlyFailure;
import com.hae5.sfaas.user.enums.UserRole;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class MonthlyFailureControllerTest extends SfaasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MonthlyFailureMapper monthlyFailureMapper;

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
        monthlyFailureMapper.deleteAll();
    }

    @Test
    @DisplayName("getFailureRate API 호출 시 200 OK와 map 반환")
    void getFailureRate_ReturnsList() throws Exception {
        // given
        MonthlyFailure f1 = MonthlyFailure.create("PR-1", "PR", 1, 0.1, 0.1, 0.1, 0.1, 20D, 2, 10D);
        MonthlyFailure f2 = MonthlyFailure.create("CB-1", "CB", 1, 0.2, 0.2, 0.2, 0.2, 30D, 3, 15D);
        monthlyFailureMapper.save(f1);
        monthlyFailureMapper.save(f2);

        // when & then
        mockMvc.perform(get("/api/v1/failures")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.PR[0].process").value("PR"))
                .andExpect(jsonPath("$.data.PR[0].failureRatePlan").value(0.1))
                .andExpect(jsonPath("$.data.CB[0].process").value("CB"))
                .andExpect(jsonPath("$.data.CB[0].failureRatePlan").value(0.2));
    }

    @Test
    @DisplayName("getFailureRate API 호출 시 빈 map 반환")
    void getFailureRate_ReturnsEmptyList() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/api/v1/failures")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("특정 공정의 불량률 데이터를 조회할 수 있다")
    void getFailureRatesByProcess_ReturnsList() throws Exception {
        // given
        MonthlyFailure f1 = MonthlyFailure.create("PR-1", "PR", 1, 0.1, 0.1, 0.1, 0.1, 20D, 2, 10D);
        MonthlyFailure f2 = MonthlyFailure.create("PR-2", "PR", 2, 0.2, 0.2, 0.2, 0.2, 30D, 3, 15D);
        monthlyFailureMapper.save(f1);
        monthlyFailureMapper.save(f2);

        // when & then
        mockMvc.perform(get("/api/v1/failures/PR")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].process").value("PR"))
                .andExpect(jsonPath("$.data[0].failureMonth").value(1))
                .andExpect(jsonPath("$.data[0].failureRatePlan").value(0.1))
                .andExpect(jsonPath("$.data[1].process").value("PR"))
                .andExpect(jsonPath("$.data[1].failureMonth").value(2))
                .andExpect(jsonPath("$.data[1].failureRatePlan").value(0.2));
    }

    @Test
    @DisplayName("존재하지 않는 공정 조회 시 빈 리스트를 반환한다")
    void getFailureRatesByProcess_ReturnsEmptyList() throws Exception {
        // given
        String nonExistentProcess = "NONEXISTENT";

        // when & then
        mockMvc.perform(get("/api/v1/failures/" + nonExistentProcess)
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
