package com.hae5.sfaas.line.controller;

import com.hae5.sfaas.common.config.security.UserDetailsImpl;
import com.hae5.sfaas.line.dto.response.MonthlyLineOperationRateResponse;
import com.hae5.sfaas.line.dto.response.QuarterLineOperationRateResponse;
import com.hae5.sfaas.line.service.LineOperationRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/line-operation-rate")
@RequiredArgsConstructor
public class LineOperationRateController {

    private final LineOperationRateService lineOperationRateService;

    @GetMapping("/quarter")
    public ResponseEntity<QuarterLineOperationRateResponse> getQuarterLineOperationRate(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        QuarterLineOperationRateResponse response = lineOperationRateService.getQuarterLineOperationRate(userDetails.getUserId());
        return ResponseEntity.ok().body(response);

    }
}
