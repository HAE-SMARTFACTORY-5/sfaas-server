package com.hae5.sfaas.line.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.common.utils.QuarterUtil;
import com.hae5.sfaas.line.dto.response.AllLineOperationRateResponse;
import com.hae5.sfaas.line.dto.response.MonthlyLineOperationRateResponse;
import com.hae5.sfaas.line.dto.response.QuarterLineOperationRateResponse;
import com.hae5.sfaas.line.model.LineOperationRate;
import com.hae5.sfaas.line.service.LineOperationRateService;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class LineOperationRateControllerTest extends SfaasApplicationTests {

    @MockitoBean
    private LineOperationRateService lineOperationRateService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("이번 분기 라인 가동률 조회")
    @Test
    public void getQuarterLineOperationRateTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();
        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());


        List<MonthlyLineOperationRateResponse> result = new ArrayList<>();
        result.add(new MonthlyLineOperationRateResponse("jan"));
        List<String> quarterMonths = QuarterUtil.getQuarterMonths(QuarterUtil.getNowQuarter());
        HashMap<String, List<MonthlyLineOperationRateResponse>> hashMap = new LinkedHashMap<>();
        hashMap.put("CT", result);

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(lineOperationRateService.getQuarterLineOperationRate(any(Long.class))).thenReturn(QuarterLineOperationRateResponse.of(quarterMonths, hashMap));

        //when & then
        mockMvc.perform(get("/api/v1/line-operation-rate/quarter", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.quarterMonths.size()").value(3))
                .andExpect(jsonPath("$.data.result.CT.size()").value(1));
    }

    @DisplayName("올해 전체 라인 가동률 조회")
    @Test
    public void getAllLineOperationRateTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();
        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());


        List<MonthlyLineOperationRateResponse> result = new ArrayList<>();
        result.add(new MonthlyLineOperationRateResponse("jan"));
        HashMap<String, List<MonthlyLineOperationRateResponse>> hashMap = new LinkedHashMap<>();
        hashMap.put("CT", result);

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(lineOperationRateService.getAllLineOperationRate(any(Long.class))).thenReturn(AllLineOperationRateResponse.from(hashMap));

        //when & then
        mockMvc.perform(get("/api/v1/line-operation-rate/all", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.result.CT.size()").value(1));
    }

}
