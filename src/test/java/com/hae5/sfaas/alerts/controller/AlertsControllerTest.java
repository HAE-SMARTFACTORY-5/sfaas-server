package com.hae5.sfaas.alerts.controller;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.mapper.AlertActionMapper;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.Alerts;
import com.hae5.sfaas.alerts.service.AlertsService;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.user.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class AlertsControllerTest extends SfaasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlertActionMapper alertActionMapper;

    @Autowired
    private AlertsMapper alertsMapper;

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

    @AfterEach
    public void cleanup() {
        alertActionMapper.deleteAllDetails();
        alertActionMapper.deleteAll();
        alertsMapper.deleteAll();
    }

    @Test
    @DisplayName("알람 내역 생성 테스트")
    void getAlerts_ReturnsList() throws Exception {
        // given
        Alerts f1 = Alerts.create("a1", "l1", "p1", "t1", LocalDateTime.now());
        Alerts f2 = Alerts.create("a2", "l2", "p2", "t2", LocalDateTime.now());

        AlertsResponse fault1 = AlertsResponse.from(f1);
        AlertsResponse fault2 = AlertsResponse.from(f2);
        List<AlertsResponse> faults = Arrays.asList(fault1, fault2);

        when(alertsService.getAlerts()).thenReturn(faults);
        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);

        // when & then
        mockMvc.perform(get("/api/v1/alerts")
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
    @DisplayName("빈 알람 내역 테스트")
    void getAlerts_ReturnsEmptyList() throws Exception {
        // given
        given(alertsService.getAlerts()).willReturn(List.of());
        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);

        // when & then
        mockMvc.perform(get("/api/v1/alerts")
                .header("Authorization", "Baerer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    /**
     * AlertActionMapper.xml 파일에서 id=save에 해당하는 H2에 해당되는 주석처리된 쿼리문을 이용하세요.
     * test 환경 : H2, dev 환경 : Mysql
     */
//    @Test
//    @DisplayName("알람 조치 등록 테스트")
//    void createAlertAction() throws Exception {
//        // given
//        String alarmId = "241212019";
//
//        Alerts alert = Alerts.create(alarmId, "L1", "P1", "FAULT", LocalDateTime.now());
//        alertsMapper.save(alert);
//
//        String requestBody = """
//                {
//                    "actionStartTime": "2024-12-15T14:00:00",
//                    "actionCompletionTime": "2024-12-15T15:30:00",
//                    "completionStatus": "COMPLETED",
//                    "faultDetail": "컨베이어 벨트 고장",
//                    "actionDetails": [
//                        {
//                            "faultType": "CBAR01",
//                            "actionDetail": "벨트 교체 완료",
//                            "maintenanceStaff": "김엔지니어",
//                            "actionTime": "2024-12-15T14:30:00"
//                        },
//                        {
//                            "faultType": "CBAR01",
//                            "actionDetail": "센서 위치 조정",
//                            "maintenanceStaff": "이엔지니어",
//                            "actionTime": "2024-12-15T15:00:00"
//                        }
//                    ]
//                }
//                """;
//
//        // when & then
//        mockMvc.perform(post("/api/v1/alerts/action/" + alarmId)
//                .header("Authorization", "Bearer test-token")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.data.alarmId").value(alarmId))
//                .andExpect(jsonPath("$.data.faultDetail").value("컨베이어 벨트 고장"))
//                .andExpect(jsonPath("$.data.actionDetails[0].faultType").value("CBAR01"))
//                .andExpect(jsonPath("$.data.actionDetails[0].actionDetail").value("벨트 교체 완료"))
//                .andExpect(jsonPath("$.data.actionDetails[0].maintenanceStaff").value("김엔지니어"))
//                .andExpect(jsonPath("$.data.actionDetails[1].faultType").value("CBAR01"))
//                .andExpect(jsonPath("$.data.actionDetails[1].actionDetail").value("센서 위치 조정"))
//                .andExpect(jsonPath("$.data.actionDetails[1].maintenanceStaff").value("이엔지니어"))
//                .andExpect(jsonPath("$.data.completionStatus").value("COMPLETED"))
//                .andExpect(jsonPath("$.data.downtime").value(90));
//    }

//    @Test
//    @DisplayName("알람 조치 수정 테스트")
//    void updateAlertAction() throws Exception {
//        // given
//        String alarmId = "241212019";
//
//        // 테스트용 알람 데이터 생성
//        Alerts alert = Alerts.create(alarmId, "L1", "P1", "FAULT", LocalDateTime.now());
//        alertsMapper.save(alert);
//
//        // 첫 번째 등록
//        String initialRequest = """
//                {
//                    "actionStartTime": "2024-12-15T14:00:00",
//                    "actionCompletionTime": "2024-12-15T15:30:00",
//                    "completionStatus": "COMPLETED",
//                    "faultDetail": "컨베이어 벨트 고장",
//                    "actionDetails": [
//                        {
//                            "faultType": "CBAR01",
//                            "actionDetail": "벨트 교체 완료",
//                            "maintenanceStaff": "김엔지니어",
//                            "actionTime": "2024-12-15T14:30:00"
//                        }
//                    ]
//                }
//                """;
//
//        mockMvc.perform(post("/api/v1/alerts/action/" + alarmId)
//                .header("Authorization", "Bearer test-token")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(initialRequest))
//                .andExpect(status().isOk());
//
//        // 수정 요청
//        String updateRequest = """
//                {
//                    "actionStartTime": "2024-12-15T14:00:00",
//                    "actionCompletionTime": "2024-12-15T15:30:00",
//                    "completionStatus": "COMPLETED",
//                    "faultDetail": "수정된 고장 내용",
//                    "actionDetails": [
//                        {
//                            "faultType": "CBAR01",
//                            "actionDetail": "벨트 교체 완료",
//                            "maintenanceStaff": "이엔지니어",
//                            "actionTime": "2024-12-15T14:30:00"
//                        }
//                    ]
//                }
//                """;
//
//        // when & then
//        mockMvc.perform(post("/api/v1/alerts/action/" + alarmId)
//                .header("Authorization", "Bearer test-token")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.data.alarmId").value(alarmId))
//                .andExpect(jsonPath("$.data.faultDetail").value("수정된 고장 내용"))
//                .andExpect(jsonPath("$.data.actionDetails[0].faultType").value("CBAR01"))
//                .andExpect(jsonPath("$.data.actionDetails[0].actionDetail").value("벨트 교체 완료"))
//                .andExpect(jsonPath("$.data.actionDetails[0].maintenanceStaff").value("이엔지니어"))
//                .andExpect(jsonPath("$.data.actionStartTime").value("2024-12-15T14:00:00"))
//                .andExpect(jsonPath("$.data.actionCompletionTime").value("2024-12-15T15:30:00"))
//                .andExpect(jsonPath("$.data.completionStatus").value("COMPLETED"));
//    }

    @Test
    @DisplayName("필수 필드 누락 시 400 에러 반환 테스트")
    void createAlertAction_ReturnsBadRequest_WhenRequiredFieldsAreMissing() throws Exception {
        // given
        String alarmId = "241212019";
        String invalidRequest = "{}";

        // when & then
        mockMvc.perform(post("/api/v1/alerts/action/" + alarmId)
                .header("Authorization", "Bearer test-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
                .andExpect(status().isBadRequest());
    }
}