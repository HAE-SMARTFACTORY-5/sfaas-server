package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertsService {

    private final AlertsMapper alertsMapper;

    // 모든 알람 내역 가져오기
    @Transactional(readOnly = true)
    public List<AlertsResponse> getAlerts() {
        return alertsMapper.findAll().stream()
                .map(AlertsResponse::from)
                .toList();
    }
}
