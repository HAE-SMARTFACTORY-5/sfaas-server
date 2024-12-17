package com.hae5.sfaas.spareParts.controller;

import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import com.hae5.sfaas.spareParts.service.SparePartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/spare-parts")
@RequiredArgsConstructor
public class SparePartsController {

    private final SparePartsService sparePartsService;

    @GetMapping
    public SparePartsResponse getSpareParts() {
        return sparePartsService.getSpareParts();
    }
}
