package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.Alerts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AlertsServiceTest extends SfaasApplicationTests {

    @Autowired
    private AlertsService alertsService;

    @Autowired
    private AlertsMapper alertsMapper;

    @BeforeEach
    void setUp() {
        alertsMapper.deleteAll();
    }

    @AfterEach
    void cleanup() {
        alertsMapper.deleteAll();
    }

    @Test
    @DisplayName("전체 알람 목록 조회 성공")
    void getAlerts_Success() {
        // given
        Alerts alert1 = Alerts.create(
                "241212019",
                "L1",
                "P1",
                "FAULT",
                LocalDateTime.of(2024, 12, 15, 14, 0));

        Alerts alert2 = Alerts.create(
                "241212020",
                "L2",
                "P2",
                "FAULT",
                LocalDateTime.of(2024, 12, 15, 15, 0));

        alertsMapper.save(alert1);
        alertsMapper.save(alert2);

        // when
        List<AlertsResponse> result = alertsService.getAlerts();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getAlarmId()).isEqualTo("241212019");
        assertThat(result.get(0).getLine()).isEqualTo("L1");
        assertThat(result.get(0).getProcess()).isEqualTo("P1");
        assertThat(result.get(1).getAlarmId()).isEqualTo("241212020");
        assertThat(result.get(1).getLine()).isEqualTo("L2");
        assertThat(result.get(1).getProcess()).isEqualTo("P2");
    }

    @Test
    @DisplayName("알람이 없을 경우 빈 리스트 반환")
    void getAlerts_ReturnsEmptyList_WhenNoAlerts() {
        // when
        List<AlertsResponse> result = alertsService.getAlerts();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}