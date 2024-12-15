package com.hae5.sfaas.preventive.service;

import com.hae5.sfaas.preventive.dto.response.PreventiveMaintenanceResponse;
import com.hae5.sfaas.preventive.mapper.PreventiveMaintenanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreventiveMaintenanceService {
    private final PreventiveMaintenanceMapper preventiveMaintenanceMapper;

    @Transactional(readOnly = true)
    public List<PreventiveMaintenanceResponse> getPreventiveMaintenance() {
        return preventiveMaintenanceMapper.findAll().stream()
                .map(PreventiveMaintenanceResponse::from)
                .toList();
    }
}
