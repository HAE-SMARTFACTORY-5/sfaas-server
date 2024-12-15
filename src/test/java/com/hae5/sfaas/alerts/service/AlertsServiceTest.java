package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.Alerts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlertsServiceTest {

    @InjectMocks
    private AlertsService alertsService;

    @Mock
    private AlertsMapper alertsMapper;

    @Test
    @DisplayName("getFault 메소드는 모든 Fault 데이터를 조회하여 FaultResponse로 변환하여 반환한다")
    void getFault_ReturnsAllFaults() {
        // given
        List<Alerts> alerts = new ArrayList<>();
        Alerts f1 = Alerts.create("a1", "l1", "p1", "t1", LocalDateTime.now());
        alerts.add(f1);
        given(alertsMapper.findAll()).willReturn(alerts);

        // when
        List<AlertsResponse> result = alertsService.getFault();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(alerts.size());
        verify(alertsMapper).findAll();
    }

    @Test
    @DisplayName("getFault 메소드는 데이터가 없을 경우 empty list를 반환한다")
    void getFault_ReturnsEmptyList_WhenNoData() {
        // given
        given(alertsMapper.findAll()).willReturn(new ArrayList<>());

        // when
        List<AlertsResponse> result = alertsService.getFault();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
        verify(alertsMapper).findAll();
    }
}