package com.hae5.sfaas.alerts.controller;

import com.hae5.sfaas.alerts.dto.response.TotalAlarmHistoryResponse;
import com.hae5.sfaas.alerts.service.AlarmHistoryService;
import com.hae5.sfaas.common.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/alarm/history")
@RequiredArgsConstructor
public class AlarmHistoryController {

    private final AlarmHistoryService alarmHistoryService;

    @GetMapping("/total")
    public ResponseEntity<TotalAlarmHistoryResponse> getNowYearTotalAlarmHistory(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        TotalAlarmHistoryResponse response = alarmHistoryService.getNowYearTotalAlarmHistory(userDetails.getUserId());
        return ResponseEntity.ok().body(response);
    }
}