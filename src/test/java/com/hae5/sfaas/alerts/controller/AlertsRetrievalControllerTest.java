package com.hae5.sfaas.alerts.controller;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.dto.response.AlertsRetrievalResponse;
import com.hae5.sfaas.alerts.model.AlertsRetrieval;
import com.hae5.sfaas.alerts.service.AlertsRetrievalService;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.user.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class AlertsRetrievalControllerTest extends SfaasApplicationTests {

        // 쿼리를 H2에서 지원하지 않음
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private AlertsRetrievalService alertsRetrievalService;
//
//    @MockitoBean
//    private JwtProvider jwtProvider;
//
//    private AccessTokenInfo accessTokenInfo;
//
//    @BeforeEach
//    void setUp() {
//        accessTokenInfo = AccessTokenInfo.of("1", UserRole.MEMBER.name());
//        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
//    }
//
//    @Test
//    @DisplayName("전체 알람 조회 테스트")
//    void getAllAlerts_ReturnsList() throws Exception {
//        // given
//        AlertsRetrievalResponse response1 = createTestResponse("241212019", "L1", "P1");
//        AlertsRetrievalResponse response2 = createTestResponse("241212020", "L2", "P2");
//        List<AlertsRetrievalResponse> responses = Arrays.asList(response1, response2);
//
//        given(alertsRetrievalService.getAllAlerts()).willReturn(responses);
//
//        // when & then
//        mockMvc.perform(get("/api/v1/alerts/retrieval")
//                .header("Authorization", "Bearer test-token"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].alarmId").value("241212019"))
//                .andExpect(jsonPath("$[0].lineId").value("L1"))
//                .andExpect(jsonPath("$[0].processId").value("P1"))
//                .andExpect(jsonPath("$[1].alarmId").value("241212020"))
//                .andExpect(jsonPath("$[1].lineId").value("L2"))
//                .andExpect(jsonPath("$[1].processId").value("P2"));
//    }
//
//    @Test
//    @DisplayName("특정 알람 ID로 조회 테스트")
//    void getAlertsByAlarmId_ReturnsAlert() throws Exception {
//        // given
//        String alarmId = "241212019";
//        AlertsRetrievalResponse response = createTestResponse(alarmId, "L1", "P1");
//        given(alertsRetrievalService.getAlertsByAlarmId(alarmId))
//                .willReturn(List.of(response));
//
//        // when & then
//        mockMvc.perform(get("/api/v1/alerts/retrieval/" + alarmId)
//                .header("Authorization", "Bearer test-token"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].alarmId").value(alarmId))
//                .andExpect(jsonPath("$[0].lineId").value("L1"))
//                .andExpect(jsonPath("$[0].processId").value("P1"));
//    }
//
//    @Test
//    @DisplayName("날짜 범위로 조회 테스트")
//    void getAlertsByDateRange_ReturnsList() throws Exception {
//        // given
//        AlertsRetrievalResponse response = createTestResponse("241212019", "L1", "P1");
//        given(alertsRetrievalService.getAlertsByDateRange(any(), any()))
//                .willReturn(List.of(response));
//
//        // when & then
//        mockMvc.perform(get("/api/v1/alerts/retrieval/date")
//                .param("startDate", "2024-01-01T00:00:00")
//                .param("endDate", "2024-12-31T23:59:59")
//                .header("Authorization", "Bearer test-token"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].alarmId").value("241212019"))
//                .andExpect(jsonPath("$[0].lineId").value("L1"))
//                .andExpect(jsonPath("$[0].processId").value("P1"));
//    }
//
//    @Test
//    @DisplayName("라인과 프로세스로 조회 테스트")
//    void getAlertsByLineAndProcess_ReturnsList() throws Exception {
//        // given
//        AlertsRetrievalResponse response = createTestResponse("241212019", "L1", "P1");
//        given(alertsRetrievalService.getAlertsByLineAndProcess("L1", "P1"))
//                .willReturn(List.of(response));
//
//        // when & then
//        mockMvc.perform(get("/api/v1/alerts/retrieval/line-process")
//                .param("lineId", "L1")
//                .param("processId", "P1")
//                .header("Authorization", "Bearer test-token"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].alarmId").value("241212019"))
//                .andExpect(jsonPath("$[0].lineId").value("L1"))
//                .andExpect(jsonPath("$[0].processId").value("P1"));
//    }
//
//    @Test
//    @DisplayName("라인만으로 조회 테스트")
//    void getAlertsByLineOnly_ReturnsList() throws Exception {
//        // given
//        AlertsRetrievalResponse response = createTestResponse("241212019", "L1", "P1");
//        given(alertsRetrievalService.getAlertsByLineAndProcess("L1", null))
//                .willReturn(List.of(response));
//
//        // when & then
//        mockMvc.perform(get("/api/v1/alerts/retrieval/line-process")
//                .param("lineId", "L1")
//                .header("Authorization", "Bearer test-token"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].alarmId").value("241212019"))
//                .andExpect(jsonPath("$[0].lineId").value("L1"));
//    }
//
//    private AlertsRetrievalResponse createTestResponse(String alarmId, String lineId, String processId) {
//        AlertsRetrieval alertsRetrieval = AlertsRetrieval.create(
//                alarmId,
//                LocalDateTime.now(),
//                lineId,
//                processId,
//                "FAULT",
//                "테스트 조치",
//                "COMPLETED",
//                LocalDateTime.now().plusHours(1));
//
//        return AlertsRetrievalResponse.from(alertsRetrieval);
//    }
}