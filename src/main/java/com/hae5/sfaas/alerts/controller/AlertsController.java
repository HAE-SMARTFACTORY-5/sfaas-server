package com.hae5.sfaas.alerts.controller;

import com.hae5.sfaas.alerts.dto.request.AlertActionRequest;
import com.hae5.sfaas.alerts.dto.response.AlertActionResponse;
import com.hae5.sfaas.alerts.dto.response.AlertsResponse;
import com.hae5.sfaas.alerts.dto.response.AlertsRetrievalResponse;
import com.hae5.sfaas.alerts.service.AlertsService;
import com.hae5.sfaas.alerts.service.AlertActionService;
import com.hae5.sfaas.alerts.service.AlertsRetrievalService;
import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
@RequiredArgsConstructor
public class AlertsController {

    private final AlertsService alertsService;
    private final AlertActionService alertActionService;
    private final AlertsRetrievalService alertsRetrievalService;

    // 모든 알람 내역 불러오기
    @GetMapping
    public ResponseEntity<List<AlertsResponse>> getAlerts() {
        List<AlertsResponse> response = alertsService.getAlerts();
        return ResponseEntity.ok().body(response);
    }

    // alarmId와 일치하는 AlertAction만 가져오기
    @GetMapping("/{alarmId}")
    public ResponseEntity<AlertActionResponse> getAlertActionByAlarmId(@PathVariable String alarmId) {
        AlertActionResponse response = alertActionService.getAlertByAlarmId(alarmId);
        return ResponseEntity.ok().body(response);
    }

    // alarmId와 일치하는 ActionDetailInfo만 가져오기
    @PostMapping("/action/{alarmId}")
    public ResponseEntity<AlertActionResponse> createAlertAction(
            @PathVariable String alarmId,
            @RequestBody AlertActionRequest request) {
        AlertActionResponse response = alertActionService.createAlertAction(alarmId, request);
        return ResponseEntity.ok().body(response);
    }

    // 모든 알람 내역 조회
    @GetMapping("/retrieval")
    public ResponseEntity<List<AlertsRetrievalResponse>> getAllAlerts() {
        return ResponseEntity.ok(alertsRetrievalService.getAllAlerts());
    }

    // 특정 알람 ID로 조회
    @GetMapping("/retrieval/{alarmId}")
    public ResponseEntity<List<AlertsRetrievalResponse>> getAlertsByAlarmId(
            @PathVariable String alarmId) {
        return ResponseEntity.ok(alertsRetrievalService.getAlertsByAlarmId(alarmId));
    }

    // 날짜 범위로 조회
    @GetMapping("/retrieval/date")
    public ResponseEntity<List<AlertsRetrievalResponse>> getAlertsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(alertsRetrievalService.getAlertsByDateRange(startDate, endDate));
    }

    // 라인과 프로세스로 조회
    @GetMapping("/retrieval/line-process")
    public ResponseEntity<List<AlertsRetrievalResponse>> getAlertsByLineAndProcess(
            @RequestParam(required = false) String lineId,
            @RequestParam(required = false) String processId) {
        return ResponseEntity.ok(alertsRetrievalService.getAlertsByLineAndProcess(lineId, processId));
    }

    @GetMapping("/facility")
    public ResponseEntity<SparePartsResponse> getFacilityAlertsByLineId(@RequestParam("line") String lineId) {
        return ResponseEntity.ok(alertsService.getFacilityAlertsByLineId(lineId));
    }
}
