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

    @Transactional(readOnly = true)
    public List<AlertsResponse> getFault() {
        return alertsMapper.findAll().stream()
                .map(AlertsResponse::from)
                .toList();
    }
}
