package com.hae5.sfaas.alerts.controller;

import com.hae5.sfaas.alerts.dto.request.AlertActionRequest;
import com.hae5.sfaas.alerts.dto.response.AlertActionResponse;
import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.service.AlertsService;
import com.hae5.sfaas.alerts.service.AlertActionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
@RequiredArgsConstructor
public class AlertsController {

    private final AlertsService alertsService;
    private final AlertActionService alertActionService;

    @GetMapping
    public ResponseEntity<List<AlertsResponse>> getFault() {
        List<AlertsResponse> response = alertsService.getFault();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/action/{alarmId}")
    public ResponseEntity<AlertActionResponse> createAlertAction(
            @PathVariable String alarmId,
            @RequestBody AlertActionRequest request) {
        AlertActionResponse response = alertActionService.createAlertAction(alarmId, request);
        return ResponseEntity.ok().body(response);
    }

    // @GetMapping("/{id})

}
