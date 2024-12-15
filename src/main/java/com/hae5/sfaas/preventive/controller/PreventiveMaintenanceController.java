package com.hae5.sfaas.preventive.controller;

import com.hae5.sfaas.preventive.dto.response.PreventiveMaintenanceResponse;
import com.hae5.sfaas.preventive.service.PreventiveMaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/maintenance/task")
@RequiredArgsConstructor
public class PreventiveMaintenanceController {
    private final PreventiveMaintenanceService preventiveMaintenanceService;

    @GetMapping
    public ResponseEntity<List<PreventiveMaintenanceResponse>> getPreventiveMaintenance(){
        List<PreventiveMaintenanceResponse> response = preventiveMaintenanceService.getPreventiveMaintenance();
        return ResponseEntity.ok().body(response);
    }
}
