package com.hae5.sfaas.quality.controller;

import com.hae5.sfaas.common.config.security.UserDetailsImpl;
import com.hae5.sfaas.quality.dto.response.QualityDefectsResponse;
import com.hae5.sfaas.quality.service.QualityDefectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quality-defects")
@RequiredArgsConstructor
public class QualityDefectsController {

    private final QualityDefectsService qualityDefectsService;

    @GetMapping("/our")
    public ResponseEntity<List<QualityDefectsResponse>> getOurQualityDefects(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<QualityDefectsResponse> response = qualityDefectsService.getOurQualityDefects(userDetails.getUserId());
        return ResponseEntity.ok().body(response);
    }
}
