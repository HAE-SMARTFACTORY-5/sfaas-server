package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.response.AlertsRetrievalResponse;
import com.hae5.sfaas.alerts.mapper.AlertsRetrievalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertsRetrievalService {
    private final AlertsRetrievalMapper alertsRetrievalMapper;

    @Transactional(readOnly = true)
    public List<AlertsRetrievalResponse> retrieveAlerts(LocalDate startDate, LocalDate endDate, String lineId,
            String processId) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

        return alertsRetrievalMapper.retrieveAlerts(startDateTime, endDateTime, lineId, processId)
                .stream()
                .map(AlertsRetrievalResponse::from)
                .toList();
    }
}
