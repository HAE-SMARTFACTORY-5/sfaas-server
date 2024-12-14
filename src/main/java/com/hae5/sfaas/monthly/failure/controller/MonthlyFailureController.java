package com.hae5.sfaas.monthly.failure.controller;

import com.hae5.sfaas.monthly.failure.dto.response.FailureRateResponse;
import com.hae5.sfaas.monthly.failure.service.MonthlyFailureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/failures")
@RequiredArgsConstructor
public class MonthlyFailureController {
    private final MonthlyFailureService monthlyFailureService;

    @GetMapping
    public ResponseEntity<Map<String, List<FailureRateResponse>>> getAllFailureRates() {
        return ResponseEntity.ok(monthlyFailureService.getAllFailureRates());
    }

    @GetMapping("/{process}")
    public ResponseEntity<List<FailureRateResponse>> getFailureRatesByProcess(@PathVariable String process) {
        return ResponseEntity.ok(monthlyFailureService.getFailureRatesByProcess(process));
    }
}
