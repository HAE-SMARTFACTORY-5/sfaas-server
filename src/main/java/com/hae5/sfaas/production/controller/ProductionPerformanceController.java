package com.hae5.sfaas.production.controller;

import com.hae5.sfaas.production.dto.response.ProductionPerformanceResponse;
import com.hae5.sfaas.production.service.ProductionPerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/production-performance")
@RequiredArgsConstructor
public class ProductionPerformanceController {

    private final ProductionPerformanceService productionPerformanceService;

    @GetMapping("/today")
    public ResponseEntity<List<ProductionPerformanceResponse>> getTodayPerformance() {
        List<ProductionPerformanceResponse> response = productionPerformanceService.getTodayPerformance();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/month")
    public ResponseEntity<List<ProductionPerformanceResponse>> getMonthPerformance() {
        List<ProductionPerformanceResponse> response = productionPerformanceService.getMonthPerformance();
        return ResponseEntity.ok().body(response);
    }
}
