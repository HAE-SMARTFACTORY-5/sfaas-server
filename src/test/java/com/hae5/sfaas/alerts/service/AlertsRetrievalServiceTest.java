package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.dto.response.AlertsRetrievalResponse;
import com.hae5.sfaas.alerts.mapper.AlertActionMapper;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.AlertAction;
import com.hae5.sfaas.alerts.model.Alerts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AlertsRetrievalServiceTest extends SfaasApplicationTests {

    // 쿼리가 H2에서 지원 하지 않음,
//    @Autowired
//    private AlertsRetrievalService alertsRetrievalService;
//
//    @Autowired
//    private AlertsMapper alertsMapper;
//
//    @Autowired
//    private AlertActionMapper alertActionMapper;
//
//    @BeforeEach
//    void setUp() {
//        alertActionMapper.deleteAllDetails();
//        alertActionMapper.deleteAll();
//        alertsMapper.deleteAll();
//    }
//
//    @AfterEach
//    void cleanup() {
//        alertActionMapper.deleteAllDetails();
//        alertActionMapper.deleteAll();
//        alertsMapper.deleteAll();
//    }
//
//    @Test
//    @DisplayName("전체 알람 조회 성공")
//    void getAllAlerts_Success() {
//        // given
//        setupTestData();
//
//        // when
//        List<AlertsRetrievalResponse> result = alertsRetrievalService.getAllAlerts();
//
//        // then
//        assertThat(result).hasSize(2);
//        assertThat(result.get(0).getAlarmId()).isEqualTo("241212019");
//        assertThat(result.get(0).getLineId()).isEqualTo("L1");
//        assertThat(result.get(0).getProcessId()).isEqualTo("P1");
//        assertThat(result.get(1).getAlarmId()).isEqualTo("241212020");
//        assertThat(result.get(1).getLineId()).isEqualTo("L2");
//        assertThat(result.get(1).getProcessId()).isEqualTo("P2");
//    }
//
//    @Test
//    @DisplayName("특정 알람 ID로 조회 성공")
//    void getAlertsByAlarmId_Success() {
//        // given
//        setupTestData();
//        String targetAlarmId = "241212019";
//
//        // when
//        List<AlertsRetrievalResponse> result = alertsRetrievalService.getAlertsByAlarmId(targetAlarmId);
//
//        // then
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0).getAlarmId()).isEqualTo(targetAlarmId);
//        assertThat(result.get(0).getLineId()).isEqualTo("L1");
//        assertThat(result.get(0).getProcessId()).isEqualTo("P1");
//    }
//
//    @Test
//    @DisplayName("날짜 범위로 조회 성공")
//    void getAlertsByDateRange_Success() {
//        // given
//        setupTestData();
//        LocalDateTime startDate = LocalDateTime.of(2024, 12, 15, 0, 0);
//        LocalDateTime endDate = LocalDateTime.of(2024, 12, 16, 0, 0);
//
//        // when
//        List<AlertsRetrievalResponse> result = alertsRetrievalService.getAlertsByDateRange(startDate, endDate);
//
//        // then
//        assertThat(result).hasSize(2);
//        assertThat(result.get(0).getActionStartTime()).isAfterOrEqualTo(startDate);
//        assertThat(result.get(0).getActionStartTime()).isBeforeOrEqualTo(endDate);
//    }
//
//    @Test
//    @DisplayName("라인과 프로세스로 조회 성공")
//    void getAlertsByLineAndProcess_Success() {
//        // given
//        setupTestData();
//        String lineId = "L1";
//        String processId = "P1";
//
//        // when
//        List<AlertsRetrievalResponse> result = alertsRetrievalService.getAlertsByLineAndProcess(lineId, processId);
//
//        // then
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0).getLineId()).isEqualTo(lineId);
//        assertThat(result.get(0).getProcessId()).isEqualTo(processId);
//    }
//
//    private void setupTestData() {
//        // 알람 데이터 생성
//        Alerts alert1 = Alerts.create(
//                "241212019",
//                "L1",
//                "P1",
//                "FAULT",
//                LocalDateTime.of(2024, 12, 15, 14, 0));
//
//        Alerts alert2 = Alerts.create(
//                "241212020",
//                "L2",
//                "P2",
//                "FAULT",
//                LocalDateTime.of(2024, 12, 15, 15, 0));
//
//        alertsMapper.save(alert1);
//        alertsMapper.save(alert2);
//
//        // 알람 조치 데이터 생성
//        AlertAction action1 = AlertAction.create(
//                "241212019",
//                "테스트 고장 1",
//                "김엔지니어",
//                LocalDateTime.of(2024, 12, 15, 14, 30),
//                LocalDateTime.of(2024, 12, 15, 15, 30),
//                60,
//                "COMPLETED");
//
//        AlertAction action2 = AlertAction.create(
//                "241212020",
//                "테스트 고장 2",
//                "이엔지니어",
//                LocalDateTime.of(2024, 12, 15, 15, 30),
//                LocalDateTime.of(2024, 12, 15, 16, 30),
//                60,
//                "COMPLETED");
//
//        alertActionMapper.save(action1);
//        alertActionMapper.save(action2);
//    }
}