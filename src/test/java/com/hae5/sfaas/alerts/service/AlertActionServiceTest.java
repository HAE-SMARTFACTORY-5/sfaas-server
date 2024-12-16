package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.dto.request.AlertActionDetailRequest;
import com.hae5.sfaas.alerts.dto.request.AlertActionRequest;
import com.hae5.sfaas.alerts.dto.response.AlertActionResponse;
import com.hae5.sfaas.alerts.mapper.AlertActionMapper;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.Alerts;
import com.hae5.sfaas.common.exception.SfaasException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AlertActionServiceTest extends SfaasApplicationTests {

    @Autowired
    private AlertActionService alertActionService;

    @Autowired
    private AlertActionMapper alertActionMapper;

    @Autowired
    private AlertsMapper alertsMapper;

    @BeforeEach
    void setUp() {
        alertActionMapper.deleteAll();
        alertsMapper.deleteAll();
    }

    @AfterEach
    void cleanup() {
        alertActionMapper.deleteAllDetails();
        alertActionMapper.deleteAll();
        alertsMapper.deleteAll();
    }

    @Test
    @DisplayName("알람 조치 등록 테스트")
    void createAlertAction_Success() {
        // given
        String alarmId = "241212019";

        Alerts alert = Alerts.create(alarmId, "L1", "P1", "FAULT", LocalDateTime.now());
        alertsMapper.save(alert);

        List<AlertActionDetailRequest> actionDetails = List.of(
                AlertActionDetailRequest.from(
                        "CBAR01",
                        "벨트 교체 완료",
                        "김엔지니어",
                        LocalDateTime.of(2024, 12, 15, 14, 30)),
                AlertActionDetailRequest.from(
                        "CBAR01",
                        "센서 위치 조정",
                        "이엔지니어",
                        LocalDateTime.of(2024, 12, 15, 15, 0)));

        AlertActionRequest request = AlertActionRequest.from(
                LocalDateTime.of(2024, 12, 15, 14, 0),
                LocalDateTime.of(2024, 12, 15, 15, 30),
                "COMPLETED",
                "컨베이어 벨트 고장",
                actionDetails);

        // when
        AlertActionResponse result = alertActionService.createAlertAction(alarmId, request);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getAlarmId()).isEqualTo(alarmId);
        assertThat(result.getFaultDetail()).isEqualTo("컨베이어 벨트 고장");
        assertThat(result.getActionDetails()).hasSize(2);
        assertThat(result.getActionDetails().get(0).getFaultType()).isEqualTo("CBAR01");
        assertThat(result.getActionDetails().get(0).getActionDetail()).isEqualTo("벨트 교체 완료");
        assertThat(result.getActionDetails().get(1).getFaultType()).isEqualTo("CBAR01");
        assertThat(result.getActionDetails().get(1).getActionDetail()).isEqualTo("센서 위치 조정");
        assertThat(result.getDowntime()).isEqualTo(90);
    }

    @Test
    @DisplayName("존재하지 않는 알람 ID로 조치 등록 시 실패")
    void createAlertAction_Fail_AlarmNotFound() {
        // given
        String nonExistentAlarmId = "999999999";
        AlertActionRequest request = AlertActionRequest.from(
                LocalDateTime.of(2024, 12, 15, 14, 0),
                LocalDateTime.of(2024, 12, 15, 15, 30),
                "COMPLETED",
                "테스트",
                List.of());

        // when & then
        assertThatThrownBy(() -> alertActionService.createAlertAction(nonExistentAlarmId, request))
                .isInstanceOf(SfaasException.class);
    }
}
