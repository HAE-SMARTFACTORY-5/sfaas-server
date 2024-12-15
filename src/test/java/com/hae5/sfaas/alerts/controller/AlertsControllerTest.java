package com.hae5.sfaas.alerts.controller;

import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.model.Alerts;
import com.hae5.sfaas.alerts.service.AlertsService;
import com.hae5.sfaas.user.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class AlertsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlertsService alertsService;

    @MockitoBean
    private JwtProvider jwtProvider;

    private AccessTokenInfo accessTokenInfo;

    @BeforeEach
    void setUp() {
        accessTokenInfo = AccessTokenInfo.of("1", UserRole.MEMBER.name());
        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
    }

    @Test
    @DisplayName("getFault API 호출 시 200 OK와 데이터 리스트를 반환")
    void getFault_ReturnsList() throws Exception {
        // given
        Alerts f1 = Alerts.create("a1", "l1", "p1", "t1", LocalDateTime.now());
        Alerts f2 = Alerts.create("a2", "l2", "p2", "t2", LocalDateTime.now());

        AlertsResponse fault1 = AlertsResponse.from(f1);
        AlertsResponse fault2 = AlertsResponse.from(f2);
        List<AlertsResponse> faults = Arrays.asList(fault1, fault2);

        when(alertsService.getFault()).thenReturn(faults);
        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);

        // when & then
        mockMvc.perform(get("/api/v1/fault")
                .header("Authorization", "Baerer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].line").value("l1"))
                .andExpect(jsonPath("$.data[0].process").value("p1"))
                .andExpect(jsonPath("$.data[1].line").value("l2"))
                .andExpect(jsonPath("$.data[1].process").value("p2"));
    }

    @Test
    @DisplayName("getFault API 호출 시 빈 리스트를 반환할 수 있다")
    void getFault_ReturnsEmptyList() throws Exception {
        // given
        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of("1", UserRole.MEMBER.name());
        given(alertsService.getFault()).willReturn(List.of());
        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);

        // when & then
        mockMvc.perform(get("/api/v1/fault")
                .header("Authorization", "Baerer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}