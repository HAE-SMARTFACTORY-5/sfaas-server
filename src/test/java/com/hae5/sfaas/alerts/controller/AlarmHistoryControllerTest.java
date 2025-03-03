package com.hae5.sfaas.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.dto.response.TotalAlarmHistoryResponse;
import com.hae5.sfaas.alerts.service.AlarmHistoryService;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class AlarmHistoryControllerTest extends SfaasApplicationTests {

    @MockitoBean
    private AlarmHistoryService alarmHistoryService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("올해 알람 히스토리 모두 조회")
    @Test
    public void getNowYearTotalAlarmHistoryTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();
        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());
        TotalAlarmHistoryResponse response = new TotalAlarmHistoryResponse();
        response.addMonthValue(1, 1);

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(alarmHistoryService.getNowYearTotalAlarmHistory(any(Long.class))).thenReturn(response);

        //when & then
        mockMvc.perform(get("/api/v1/alarm/history/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.jan").value("1"));
    }

}