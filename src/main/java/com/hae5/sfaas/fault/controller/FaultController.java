package com.hae5.sfaas.fault.controller;

import com.hae5.sfaas.fault.dto.response.FaultResponse;
import com.hae5.sfaas.fault.service.FaultService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/fault")
@RequiredArgsConstructor
public class FaultController {

    private final FaultService faultService;

    @GetMapping
    public ResponseEntity<List<FaultResponse>> getFault() {
        List<FaultResponse> response = faultService.getFault();
        return ResponseEntity.ok().body(response);
    }

}
