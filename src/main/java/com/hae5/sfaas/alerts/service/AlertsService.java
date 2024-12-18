package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.Alerts;
import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public SparePartsResponse getFacilityAlertsByLineId(String lineId) {
        Map<String, List<Alerts>> alerts = alertsMapper.findAllByLineId(lineId).stream()
                .collect(Collectors.groupingBy(
                        Alerts::getProcess,
                        Collectors.toList()
                ));
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(0);
        result.add(0);
        result.add(0);
        result.add(0);

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("PR", 0);
        hashMap.put("CB", 1);
        hashMap.put("CM", 2);
        hashMap.put("CT", 3);

        for (String key : alerts.keySet()) {
            if (!alerts.get(key).isEmpty()) {
                Integer index = hashMap.get(key);
                result.set(index, 1);
            }
        }

        return SparePartsResponse.of(result, lineId);
    }
}
