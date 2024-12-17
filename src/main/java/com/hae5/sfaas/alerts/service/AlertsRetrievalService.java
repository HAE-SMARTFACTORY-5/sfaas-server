package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.response.AlertsRetrievalResponse;
import com.hae5.sfaas.alerts.mapper.AlertsRetrievalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertsRetrievalService {
    private final AlertsRetrievalMapper alertsRetrievalMapper;

    @Transactional(readOnly = true)
    public List<AlertsRetrievalResponse> getAllAlerts() {
        return alertsRetrievalMapper.findAll().stream()
                .map(AlertsRetrievalResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AlertsRetrievalResponse> getAlertsByAlarmId(String alarmId) {
        return alertsRetrievalMapper.findByAlarmId(alarmId).stream()
                .map(AlertsRetrievalResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AlertsRetrievalResponse> getAlertsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return alertsRetrievalMapper.findByDateRange(startDate, endDate).stream()
                .map(AlertsRetrievalResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AlertsRetrievalResponse> getAlertsByLineAndProcess(String lineId, String processId) {
        return alertsRetrievalMapper.findByLineAndProcess(lineId, processId).stream()
                .map(AlertsRetrievalResponse::from)
                .toList();
    }
}
