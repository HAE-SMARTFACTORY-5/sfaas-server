package com.hae5.sfaas.alerts.controller;

import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.service.AlertsService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fault")
@RequiredArgsConstructor
public class AlertsController {

    private final AlertsService alertsService;

    @GetMapping
    public ResponseEntity<List<AlertsResponse>> getFault() {
        List<AlertsResponse> response = alertsService.getFault();
        return ResponseEntity.ok().body(response);
    }

}
