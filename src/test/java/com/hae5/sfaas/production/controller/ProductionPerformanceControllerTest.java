package com.hae5.sfaas.production.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.production.dto.response.ProductionPerformanceResponse;
import com.hae5.sfaas.production.service.ProductionPerformanceService;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ProductionPerformanceControllerTest extends SfaasApplicationTests {

    @MockitoBean
    private ProductionPerformanceService productionPerformanceService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("오늘 생산실적 조회")
    @Test
    public void getTodayPerformanceTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());

        List<ProductionPerformanceResponse> response = new ArrayList<>();
        response.add(new ProductionPerformanceResponse("프레스", 10, 9));


        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(productionPerformanceService.getTodayPerformance()).thenReturn(response);

        //when & then
        mockMvc.perform(get("/api/v1/production-performance/today")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isOk());
    }

    @DisplayName("이번달 생산 계획 및 실적 조회")
    @Test
    public void getMonthPerformanceTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());

        List<ProductionPerformanceResponse> response = new ArrayList<>();
        response.add(new ProductionPerformanceResponse("프레스", 10, 9));


        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(productionPerformanceService.getMonthPerformance()).thenReturn(response);

        //when & then
        mockMvc.perform(get("/api/v1/production-performance/month")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isOk());
    }

}
